package com;

import com.exception.ConstructorNotFoundException;
import com.exception.TooManyConstructorsException;
import junit.framework.TestCase;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;

public class InjectorImlTest extends TestCase {

    //todo add tests on exception
    private final Injector injector = new InjectorIml();

    @Test
    public void testBind()
            throws TooManyConstructorsException, ConstructorNotFoundException,
            InvocationTargetException, NoSuchMethodException,
            InstantiationException, IllegalAccessException {

        injector.bind(EventDao.class, EventService.class);
        assertEquals(injector.getProvider(EventDao.class), injector.getProvider(EventDao.class));
    }

    @Test
    public void testBindSingleton()
            throws TooManyConstructorsException, ConstructorNotFoundException,
            InvocationTargetException, NoSuchMethodException,
            InstantiationException, IllegalAccessException {

        injector.bindSingleton(EventDao.class, EventService.class);
        assertEquals(injector.getProvider(EventDao.class), injector.getProvider(EventDao.class));
    }
}