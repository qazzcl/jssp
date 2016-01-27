package jssp.server.message;


import com.google.common.collect.Maps;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import static jssp.util.ReflectionUtil.nonPubMemberFieldsOf;
import static jssp.util.ReflectionUtil.publicMemberFieldsOf;

public class Unmarshaller<T extends CalcMessage> {

    private final Class<T> clazz;
    private final List<Field> publicFields;
    private final Map<String, Method> setterMethods;

    public Unmarshaller(Class<T> clazz) {
        this.clazz = clazz;
        this.publicFields = publicMemberFieldsOf(clazz);
        this.setterMethods = settersOfNonPubMembers(clazz);
    }

    public T unmarshal(MapMessage message) {
        try {
            T result = clazz.getConstructor().newInstance();
            for (Field publicField : this.publicFields) {
                Object v = getValue(message, publicField.getName());
                if (v != null) {
                    publicField.set(result, v);
                }
            }
            for (Map.Entry<String, Method> entry : setterMethods.entrySet()) {
                Object v = getValue(message, entry.getKey());
                if(v != null){
                    entry.getValue().invoke(result, v);
                }
            }
            return result;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static <T> T getValue(MapMessage mapMessage, String name) throws JMSException {
        Object rt = null;
        if (mapMessage.itemExists(name)) {
            rt = mapMessage.getObject(name);
        }
        return (T) rt;

    }

    private static Map<String, Method> settersOfNonPubMembers(Class clazz)  {
        Map<String, Method> result = Maps.newHashMap();
        List<Field> fields = nonPubMemberFieldsOf(clazz);
        for (Field field : fields) {
            PropertyDescriptor propertyDescriptor;
            try {
                propertyDescriptor = new PropertyDescriptor(field.getName(), clazz);
            } catch (IntrospectionException e) {
                throw new RuntimeException(e);
            }
            Method setter = propertyDescriptor.getWriteMethod();
            if(setter != null){
                result.put(field.getName(), setter);
            }
        }
        return result;
    }

}
