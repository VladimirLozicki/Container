package com;

import java.util.HashMap;
import java.util.Map;

public class Singleton {

    private static Singleton instance;

    private final Map<Class, Object> mapHolder = new HashMap();

    private Singleton() {
    }

    public static <T> T getInstance(Class<T> tClass) throws IllegalAccessException, InstantiationException {
        if (instance == null) instance = new Singleton();

        if (!instance.mapHolder.containsKey(tClass)) {
            T obj = tClass.newInstance();
            instance.mapHolder.put(tClass, obj);
        }
        return (T) instance.mapHolder.get(tClass);
    }
}
