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
//            return null;
            return "暂无，请查看该处属性设置是否正确01？" + propertyName;
        }
        field.setAccessible(true);
        return field.get(obj) == null ? "暂无，请查看该处属性设置是否正确02？" + propertyName : field.get(obj);
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
