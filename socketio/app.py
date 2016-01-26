import os
import re
import sys

from gevent import monkey
monkey.patch_all()
from flask import Flask
from flask_socketio import SocketIO

async_mode = 'gevent'
host = '0.0.0.0'
port = 5001

if len(sys.argv) > 1:
    port = int(sys.argv[1])

app = Flask(__name__)
socketio = SocketIO(app, async_mode=async_mode)


if __name__ == '__main__':
    path = './server'
    files = os.listdir(path)
    test = re.compile('.*\.py$')
    files = filter(test.search, files)
    module_names = map(lambda f:os.path.splitext(f)[0], files)
    sys.path.append(path)
    for module_name in module_names:
        try:
            module = __import__(module_name)
            if not getattr(module, 'enabled', False):
                del module
                continue
            module.socketio = socketio
            if hasattr(module, 'register_events'):
                getattr(module, 'register_events')()
            subscriber = getattr(module, 'subscriber', None)
            if subscriber:
                subscriber.start()

        except (ImportError, TypeError):
            pass

    sys.path.remove(path)

    socketio.run(app, host=host, port=port)

