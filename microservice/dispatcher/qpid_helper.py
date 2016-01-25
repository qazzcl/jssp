import time
import uuid
from Queue import Queue
from threading import Thread
from threading import Condition
import traceback
from qpid import messaging
from qpid.messaging import Message

message_id_tag = u'x-amqp-0-10.app-id'


class Receiver(object):
    """
    qpid subscriber
    """

    def __init__(self):
        self.handlers = []

    def start(self):
        for broker, address, handler in self.handlers:
            handler.start()

    def on(self, broker, address, **kwargs):
        """Decorator to register a receive message handler.

        This decorator must be applied to receive message handlers. Example::

            @receiver.on(broker, address, work_thread_num=2)
            def message_processor(message):
                print message
                reply = Message('test')
                return reply

            :param broker: qpid broker ip
            :param address: url of the qpid queue
        """

        def decorator(f):
            def msg_processor(queue):
                while 1:
                    message = queue.get()
                    if not isinstance(message, Message):
                        continue
                    try:
                        reply = f(message)
                        if reply and isinstance(reply, Message):
                            reply_to = message.reply_to
                            message_id = message.properties.get(message_id_tag, None)
                            if message_id:
                                reply.properties[message_id_tag] = message_id
                            Receiver.reply(broker, reply_to, reply)
                    except Exception:
                        print traceback.format_exc()

            handler = _Handler()
            work_thread_num = kwargs.pop('work_thread_num', 1)
            for i in range(work_thread_num):
                work_thread = Thread(target=msg_processor, args=(handler.queue,))
                handler.work_threads.append(work_thread)
            address_queue = address + '; { create: always, }'
            handler.message_receiver = _MessageReceiver(broker, address_queue)
            self.handlers.append((broker, address, handler))

        return decorator

    @staticmethod
    def reply(broker, address, message):
        connection = messaging.Connection(broker)
        try:
            connection.open()
            session = connection.session()
            sender = session.sender(address)
            sender.send(message)
            session.acknowledge()
        except messaging.MessagingError:
            print traceback.format_exc()
            return False
        finally:
            connection.close()

        return True


class Sender(object):
    """
    qpid sender
    """

    def __init__(self, broker, address):
        self.broker = broker
        self.address = address
        self.address_self = str(uuid.uuid1()) + ''';{
                            create: always ,
                            delete: always ,
                            node: {type:queue , x-declare : {auto-delete: true}}
                        }'''
        self.ret_msg_queue = Queue()
        self.cond = Condition()
        self.send_callback = {}
        self.send_response = {}
        self.connection = None
        self.session = None
        self.sender = None

    def init(self):
        self.connection = messaging.Connection(self.broker)
        try:
            self.connection.open()
            self.session = self.connection.session()
            self.sender = self.session.sender(self.address)
        except messaging.MessagingError:
            print traceback.format_exc()
            return False

        thread = Thread(target=self.ret_msg_processor)
        thread.daemon = True
        thread.start()

        message_receiver = _MessageReceiver(self.broker, self.address_self)
        message_receiver.start(self.ret_msg_queue, daemon=True)

        return True

    def destroy(self):
        self.connection.close()

    def send(self, message, callback=None):
        if self.sender is None:
            return False

        message_id = uuid.uuid1().hex
        message.reply_to = self.address_self
        message.properties[message_id_tag] = message_id
        if callback:
            self.send_callback[message_id] = callback
        try:
            self.sender.send(message)
            self.session.acknowledge()
        except messaging.MessagingError:
            print traceback.format_exc()
            return False

        return True

    def sync_send(self, message, timeout=1):
        if self.sender is None:
            return None

        message_id = uuid.uuid1().hex
        message.reply_to = self.address_self
        message.properties[message_id_tag] = message_id
        try:
            self.send_response[message_id] = None
            self.sender.send(message)
            self.session.acknowledge()
        except messaging.MessagingError:
            print traceback.format_exc()
            return None

        self.cond.acquire()
        request_time = time.time()
        while self.send_response[message_id] is None and request_time + timeout > time.time():
            self.cond.wait(0.1)
        self.cond.release()
        response = self.send_response.pop(message_id, None)

        return response

    def ret_msg_processor(self):
        while 1:
            message = self.ret_msg_queue.get()
            if not isinstance(message, Message):
                continue
            message_id = message.properties.get(message_id_tag, None)
            if message_id:
                if message_id in self.send_response.keys():
                    self.cond.acquire()
                    self.send_response[message_id] = message
                    self.cond.notifyAll()
                    self.cond.release()
                if message_id in self.send_callback.keys():
                    callback = self.send_callback.get(message_id, None)
                    if callback and hasattr(callback, '__call__'):
                        ret = callback(message)
                        if ret is None or ret:
                            self.send_callback.pop(message_id, None)
                    else:
                        self.send_callback.pop(message_id, None)


class Publisher(object):
    """
    qpid publisher
    """

    def __init__(self, broker, address):
        self.broker = broker
        self.address = address + '; { create: always, node: {type: topic}, }'
        self.connection = None
        self.session = None
        self.sender = None

    def init(self):
        self.connection = messaging.Connection(self.broker)
        try:
            self.connection.open()
            self.session = self.connection.session()
            self.sender = self.session.sender(self.address)
        except messaging.MessagingError:
            print traceback.format_exc()
            return False

        return True

    def destroy(self):
        self.connection.close()

    def publish(self, message):
        if self.sender is None:
            return False

        try:
            self.sender.send(message)
            self.session.acknowledge()
        except messaging.MessagingError:
            print traceback.format_exc()
            return False

        return True


class Subscriber(object):
    """
    qpid subscriber
    """

    def __init__(self):
        self.handlers = []

    def start(self):
        for broker, address, handler in self.handlers:
            handler.start()

    def on(self, broker, address, **kwargs):
        """Decorator to register a receive message handler.

        This decorator must be applied to receive message handlers. Example::

            @subscriber.on(broker, address, work_thread_num=2)
            def message_processor(message):
                print message

            :param broker: qpid broker ip
            :param address: url of the qpid queue
        """

        def decorator(f):
            def msg_processor(queue):
                while 1:
                    message = queue.get()
                    if not isinstance(message, Message):
                        continue
                    try:
                        f(message)
                    except Exception:
                        print traceback.format_exc()

            handler = _Handler()
            work_thread_num = kwargs.pop('work_thread_num', 1)
            for i in range(work_thread_num):
                work_thread = Thread(target=msg_processor, args=(handler.queue,))
                handler.work_threads.append(work_thread)
            address_queue = '#.' + address + '; {create: always, delete:always, node: {type: queue, x-declare: {auto-delete: true}, x-bindings: [{exchange: ' + repr(address) + '}]}}'
            # address_topic = address + '; { node: {type: topic}, }'
            handler.message_receiver = _MessageReceiver(broker, address_queue)
            self.handlers.append((broker, address, handler))

        return decorator


class _Handler(object):
    """
    message handler
    """

    def __init__(self):
        self.queue = Queue()
        self.message_receiver = None
        self.work_threads = []

    def start(self):
        for thread in self.work_threads:
            thread.start()
        self.message_receiver.start(self.queue)


class _MessageReceiver(object):
    def __init__(self, broker, address):
        self.broker = broker
        self.address = address
        self.connection = None
        self.session = None
        self.sender = None

    def start(self, queue, daemon=False):
        self.connection = messaging.Connection(self.broker)
        try:
            self.connection.open()
            self.session = self.connection.session()
            self.receiver = self.session.receiver(self.address)
        except messaging.MessagingError:
            print traceback.format_exc()
            return

        thread = Thread(target=self.message_receiver, args=(queue,))
        thread.setDaemon(daemon)
        thread.start()

    def message_receiver(self, queue):
        """
        Function for message receive thread
        :param broker: qpid broker ip
        :param address: url of the qpid queue
        :param queue: internal message queue to store qpid message
        """
        print 'message receiver thread for', self.broker, self.address
        while 1:
            try:
                message = self.receiver.fetch()
                queue.put(message)
                self.session.acknowledge()
            except messaging.MessagingError:
                print traceback.format_exc()

        self.connection.close()
