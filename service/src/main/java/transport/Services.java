package transport;

import com.google.common.base.Stopwatch;
import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.JMSException;
import java.lang.reflect.Field;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class Services {

    private static final Logger logger = LoggerFactory.getLogger(Services.class);
    private Map<String, CalculationService> services = Maps.newHashMap();

    public Services(CalculationService... services) {

        for (CalculationService service : services) {
            register(service);
        }
    }

    public boolean register(CalculationService service) {
        try {
            Field field = service.getClass().getField("name");
            String name = (String) field.get(null); // of static field
            services.put(name, service);
            return true;
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
            return false;
        }
    }

    public CalcMessage calc(CalcMessage requestMessage, String type) {
        Stopwatch clock = Stopwatch.createStarted();
        CalcMessage response = requestMessage;
        try {
            response = doCalc(requestMessage, type);
        } catch (Exception e) {
            response.errorCode = CalcMessage.ERROR_UNKNOWN;
            response.errorMsg = e.getMessage();
            logger.error("calc exception of " + requestMessage.getClass().getSimpleName(), e);
        }
        response.setExecutionTime(clock.elapsed(TimeUnit.MICROSECONDS) / 1E6f);
        logger.info("calc of [{}] message spent: {} ", requestMessage.getClass().getSimpleName(), clock);
        return response;
    }

    private CalcMessage doCalc(CalcMessage requestMessage, String type) throws JMSException {
        return (CalcMessage) services.get(type).calc(requestMessage);
    }

}
