package transport;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.apache.qpid.client.AMQConnection;
import org.apache.qpid.url.URLSyntaxException;

import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Queue;
import javax.jms.Session;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class MessageListener {

    private final AMQConnection connection;
    private final String queueName;
    private final MessageProcessor messageProcessor;
    private final int threadNum;

    public MessageListener(AMQConnection connection, String queueName, MessageProcessor messageProcessor, int threadSize) {
        this.connection = connection;
        this.queueName = queueName;
        this.messageProcessor = messageProcessor;
        this.threadNum = threadSize;
    }

    public void start() {
        ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("cal-thread-%d").build();
        final ExecutorService executorService = Executors.newFixedThreadPool(threadNum, threadFactory);
        MessageConsumer consumer;
        try {
            consumer = consumer();
            consumer.setMessageListener(message -> executorService.submit(() -> messageProcessor.process(message)));
        } catch (URLSyntaxException | JMSException e) {
            throw new RuntimeException(e);
        }
    }

    private MessageConsumer consumer() throws URLSyntaxException, JMSException {
        Session consumerSession = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Session producerSession = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Queue queue = producerSession.createQueue(queueName);
        MessageConsumer consumer = consumerSession.createConsumer(queue);
        connection.start();
        return consumer;
    }

}
