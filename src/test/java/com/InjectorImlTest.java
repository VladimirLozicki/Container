package com;

import com.exception.ConstructorNotFoundException;
import com.exception.TooManyConstructorsException;
import junit.framework.TestCase;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;

public class InjectorImlTest extends TestCase {

    private final Injector injector = new InjectorIml();

    public void testGetProvider() {
    }

    @Test(expected = TooManyConstructorsException.class)
    public void testBind()
            throws TooManyConstructorsException, ConstructorNotFoundException,
            InvocationTargetException, NoSuchMethodException,
            InstantiationException, IllegalAccessException {

        injector.bind(EventDao.class, EventService.class);
    }

    public void testBindSingleton() {
    }
}