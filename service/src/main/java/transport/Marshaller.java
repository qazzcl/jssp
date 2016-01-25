package transport;


import com.google.common.collect.Maps;
import util.ReflectionUtil;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

public class Marshaller<T> {

    private final Map<Class, List<Field>> fieldsMap = Maps.newConcurrentMap();

    public Map<String, Object> marshal(T response) {
        Map<String, Object> result = Maps.newHashMap();
        List<Field> fields = fieldsOf(response);
        for (Field field : fields) {
            try {
                Object value = field.get(response);
                if (value != null) {
                    result.put(field.getName(), value);
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return result;
    }

    private List<Field> fieldsOf(T response) {
        Class<?> clazz = response.getClass();
        if(!fieldsMap.containsKey(clazz)){
            fieldsMap.put(clazz, ReflectionUtil.publicMemberFieldsOf(clazz));
        }
        return fieldsMap.get(clazz);
    }
}
