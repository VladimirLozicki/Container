package com;


import com.exception.ConstructorNotFoundException;
import com.exception.TooManyConstructorsException;

import java.lang.reflect.InvocationTargetException;

public class Main {

    public static void main(String[] args)
            throws TooManyConstructorsException, ConstructorNotFoundException, InvocationTargetException,
            NoSuchMethodException, InstantiationException, IllegalAccessException {

        Injector injector = new InjectorIml();
        injector.bind(EventDao.class, EventService.class);
        System.out.println(injector.getProvider(EventDao.class));
        injector.bind(EventDao.class, EventService.class);
        System.out.println(injector.getProvider(EventDao.class));
    }
}
