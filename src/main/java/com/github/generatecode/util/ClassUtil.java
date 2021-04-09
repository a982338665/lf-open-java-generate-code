package com.github.generatecode.util;

import java.lang.reflect.Field;


/**
 * @author luofeng
 */
public class ClassUtil {

    public static Object getPropertyValue(Object obj, String propertyName) throws IllegalAccessException {
        Class<?> Clazz = obj.getClass();
        Field field;
        if ((field = getField(Clazz, propertyName)) == null) {
            return null;
        }
        field.setAccessible(true);
        return field.get(obj);
    }

    public static Field getField(Class<?> clazz, String propertyName) {
        if (clazz == null) {
            return null;
        }
        try {
            return clazz.getDeclaredField(propertyName);
        } catch (NoSuchFieldException e) {
            return getField(clazz.getSuperclass(), propertyName);
        }
    }
}
