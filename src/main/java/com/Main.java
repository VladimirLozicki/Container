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

        Provider<EventDao> daoProvider = injector.getProvider(EventDao.class);
        //  System.out.println(daoProvider);

    }
}
