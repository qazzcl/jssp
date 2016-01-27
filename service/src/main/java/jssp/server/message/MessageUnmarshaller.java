package jssp.server.message;

import javax.jms.MapMessage;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class MessageUnmarshaller {

    private static final Map<String, Unmarshaller> unmarshallers = new HashMap<>();

    public boolean register(Class messageClass) {
        try {
            Field field = messageClass.getField("name");
            String name = (String) field.get(null); // of static field
            unmarshallers.put(name, new Unmarshaller(messageClass));
            return true;
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
            return false;
        }
    }

    public CalcMessage unmarshal(MapMessage message, String type) {
        return unmarshallers.get(type).unmarshal(message);
    }
}
