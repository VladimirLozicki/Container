package com;

import com.exception.ConstructorNotFoundException;
import com.exception.TooManyConstructorsException;

import java.lang.reflect.InvocationTargetException;

public class SingletonBinding {
    public static void main(String[] args) throws IllegalAccessException, InvocationTargetException,
            TooManyConstructorsException, InstantiationException, ConstructorNotFoundException, NoSuchMethodException {
        Injector injector = new InjectorIml();
        injector.bindSingleton(EventDao.class, EventService.class);

        System.out.println(injector.getProvider(EventDao.class));

        injector.bindSingleton(EventDao.class, EventService.class);

        System.out.println(injector.getProvider(EventDao.class));
    }
}
