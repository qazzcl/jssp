package util;

import org.apache.commons.lang3.reflect.FieldUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.List;
import java.util.function.Predicate;

import static com.google.common.collect.Lists.newArrayList;

public class ReflectionUtil {

    public static List<Field> publicMemberFieldsOf(Class clazz) {
        return fieldsOf(clazz, input -> nonStaticPublic(input.getModifiers()));
    }

    public static List<Field> nonPubMemberFieldsOf(Class clazz) {
        return fieldsOf(clazz, input -> nonStaticNonPublic(input.getModifiers()));
    }

    private static List<Field> fieldsOf(Class clazz, Predicate<Field> predicate) {
        return ListUtils.filter(newArrayList(FieldUtils.getAllFields(clazz)), predicate);
    }

    public static List<Method> publicMemberMethodOf(Class clazz) {
        return ListUtils.filter(newArrayList(clazz.getMethods()), input -> nonStaticPublic(input.getModifiers()));
    }

    private static boolean nonStaticNonPublic(int modifiers){
        return !Modifier.isStatic(modifiers) &&
                (Modifier.isPrivate(modifiers) || Modifier.isProtected(modifiers));
    }

    private static boolean nonStaticPublic(int modifiers) {
        return !Modifier.isStatic(modifiers) && Modifier.isPublic(modifiers);
    }
}
