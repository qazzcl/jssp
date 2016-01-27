package jssp.server.transport;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import jssp.server.message.CalcMessage;
import jssp.server.message.Marshaller;
import jssp.server.message.MessageUnmarshaller;
import jssp.server.service.Services;
import org.apache.qpid.client.AMQAnyDestination;
import org.apache.qpid.client.AMQConnection;
import org.apache.qpid.client.AMQConnectionFactory;
import org.apache.qpid.client.AMQSession;
import org.apache.qpid.url.URLSyntaxException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.*;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class MessageProcessor {

    private static Logger logger = LoggerFactory.getLogger(MessageProcessor.class);

    private final Services services;
    private final Marshaller marshaller;
    private final MessageUnmarshaller messageUnmarshaller;

    private final ExecutorService executorService;
    private final Map<String, SessionAndProducer> producers = Maps.newConcurrentMap();

    private volatile AMQConnection connection;
    private final String messageIdName = "x-amqp-0-10.app-id";

    public MessageProcessor(String qpidUrl) {
        this.services = new Services();
        this.marshaller = new Marshaller();
        this.messageUnmarshaller = new MessageUnmarshaller();

        ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("response-sending-thread").build();
        this.executorService = Executors.newSingleThreadExecutor(threadFactory);

        try {
            connection = createConnection(qpidUrl);
        } catch (Exception e) {
            logger.error("error to start producer ", e);
            System.exit(1);
        }
    }

    private AMQConnection createConnection(final String qpidUrl) throws JMSException, URLSyntaxException {
        final AMQConnection conn = (AMQConnection) new AMQConnectionFactory(qpidUrl).createConnection();
        conn.setExceptionListener(exception -> {
            try {

                List<AMQSession> sessions = Lists.newArrayList();
                for (SessionAndProducer sessionAndProducer : producers.values()) {
                    sessions.add((AMQSession) sessionAndProducer.session);
                }
                conn.close(1000);
                conn.close();
                producers.clear();
                logger.info("cleared all live producers");
                connection = createConnection(qpidUrl);
            } catch (Exception e) {
                logger.info("error to close broken connections");
            }
        });
        conn.start();
        logger.info("process connection started.");
        return conn;
    }

    public void process(Message requestMessage) {
        if (requestMessage instanceof MapMessage) {
            try {
                MapMessage mapRequest = (MapMessage) requestMessage;
                String type = mapRequest.getJMSCorrelationID();
                CalcMessage calRequest = messageUnmarshaller.unmarshal(mapRequest, type);
                logRequest(requestMessage, calRequest);
                CalcMessage response = services.calc(calRequest, type);
                sendResponse(response, requestMessage);
            } catch (Throwable e) {
                logger.error("unknown error when process " + requestMessage, e);
                e.printStackTrace();
            }
        } else {
            logger.warn("unknown requestMessage received " + requestMessage);
        }
    }

    private void logRequest(Message requestMessage, CalcMessage calRequest) {
        logger.info("got requestMessage : " + requestMessage);
    }

    private void sendResponse(final CalcMessage result, final Message request) {
        executorService.submit(() -> {
            try {
                AMQAnyDestination repliedQueue = (AMQAnyDestination) request.getJMSReplyTo();
                SessionAndProducer sessionAndProducer = messageProducer(repliedQueue.getTopicName());

                MapMessage response = sessionAndProducer.session.createMapMessage();
                response.setObject("AckMsgHead", head(result));
                response.setObject("AckMsgBody", marshaller.marshal(result));

                cloneProperties(request, response);

                sessionAndProducer.messageProducer.send(response);
                logResponse(response, result);
            } catch (Exception e) {
                logger.error("unable to send response " + result, e);
            }
        });
    }

    private void logResponse(MapMessage response, CalcMessage result) {
            logger.info("sent response: {}", response);
    }

    private Map<String, Object> head(CalcMessage calcResult) {
        Map<String, Object> result = Maps.newHashMap();
        result.put("retcode", calcResult.errorCode.shortValue());

        if (calcResult.errorCode != 0) {
            result.put("desc", calcResult.errorMsg);
        }
        result.put("exectime", calcResult.getExecutionTime());
        result.put("idx", (short) 0);
        result.put("total", (short) 1);
        return result;
    }

    private void cloneProperties(Message request, MapMessage response) throws JMSException {
        response.setJMSCorrelationID(request.getJMSCorrelationID());

        if (request.propertyExists(messageIdName)) {
            response.setStringProperty(messageIdName, request.getStringProperty(messageIdName));
        }

        for (Enumeration properties = request.getPropertyNames(); properties.hasMoreElements(); ) {
            String name = (String) properties.nextElement();
            String value = request.getStringProperty(name);
            if (value != null) {
                response.setStringProperty(name, value);
            }
        }
    }

    private SessionAndProducer messageProducer(String queueName) {
        if (!producers.containsKey(queueName)) {
            try {
                Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
                Queue queue = session.createQueue(queueName);
                MessageProducer producer = session.createProducer(queue);
                logger.info("created producer on queue " + queueName);
                producers.put(queueName, new SessionAndProducer(session, producer));
            } catch (JMSException e) {
                logger.error("error to create producer on queue url: " + queueName, e);
            }
        }
        return producers.get(queueName);
    }

    private static class SessionAndProducer {
        final Session session;
        final MessageProducer messageProducer;

        private SessionAndProducer(Session session, MessageProducer messageProducer) {
            this.session = session;
            this.messageProducer = messageProducer;
        }
    }
}
