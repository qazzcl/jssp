package jssp.server;

import jssp.server.transport.MessageListener;
import jssp.server.transport.MessageProcessor;
import org.apache.qpid.client.AMQConnection;
import org.apache.qpid.client.AMQConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.Properties;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        System.out.println("starting Service ... ");
        try {
            InputStream propertyStream = Main.class.getClassLoader().getResourceAsStream("jssp.properties");
            Properties properties = new Properties();
            properties.load(propertyStream);

            String qpidUrl = (String) properties.get("qpid.url");
            String queueName = (String) properties.get("qpid.queue");
            logger.info("using qpid url={}, queue={}", qpidUrl, queueName);
            System.out.println(String.format("using qpid url=%s, queue=%s", qpidUrl, queueName));
            // int threadSize = Runtime.getRuntime().availableProcessors() - 1;
            int threadSize = 1;

            MessageListener messageListener = new MessageListener(
                    (AMQConnection) new AMQConnectionFactory(qpidUrl).createConnection(),
                    queueName,
                    new MessageProcessor(qpidUrl),
                    threadSize);
            messageListener.start();

        } catch (Exception e) {
            logger.error("Severe: ", e);
            e.printStackTrace();
            System.out.println("Exiting...");
            System.exit(1);
        }
    }

}
