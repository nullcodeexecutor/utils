package org.coder.utils.bean;

import java.lang.reflect.Field;

/**
 * Created by coder on 15/4/25.
 */
public class BeanInitUtil {

    private BeanInitUtil() {
    }


    /**
     * 被初始化的类必须是public的
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T initBean(Class<T> clazz) {
        T t = null;
        try {
            t = clazz.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        initBean(t);
        return t;
    }

    public static <T> void initBean(T t) {
        Field[] fields = t.getClass().getDeclaredFields();
        for (Field field : fields) {
            try {
                initField(t, field);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    private static void initField(Object obj, Field field) throws IllegalAccessException {
        field.setAccessible(true);
        Class<?> clazz = field.getType();
        if (null != field.get(obj)) {
            return;
        }
        if (clazz.equals(String.class)) {
            field.set(obj, "");
        } else if (clazz.equals(Byte.class)) {
            field.set(obj, (byte) 0);
        } else if (clazz.equals(Short.class)) {
            field.set(obj, (short) 0);
        } else if (clazz.equals(Integer.class)) {
            field.set(obj, 0);
        } else if (clazz.equals(Long.class)) {
            field.set(obj, 0l);
        } else if (clazz.equals(Float.class)) {
            field.set(obj, 0.0f);
        } else if (clazz.equals(Double.class)) {
            field.set(obj, 0.0d);
        } else if (clazz.equals(Character.class)) {
            field.set(obj, ' ');
        } else if (clazz.equals(Boolean.class)) {
            field.set(obj, false);
        }
    }

}
