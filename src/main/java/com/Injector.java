package com;

import com.exception.ConstructorNotFoundException;
import com.exception.TooManyConstructorsException;

import java.lang.reflect.InvocationTargetException;

public interface Injector {
    <T> Provider<T> getProvider(Class<T> type)
            throws NoSuchMethodException, IllegalAccessException,
            InvocationTargetException, InstantiationException;

    <T> void bind(Class<T> intf, Class<? extends T> impl)
            throws TooManyConstructorsException, ConstructorNotFoundException, IllegalAccessException,
            InstantiationException, NoSuchMethodException, InvocationTargetException;

    <T> void bindSingleton(Class<T> intf, Class<? extends T> impl)
            throws TooManyConstructorsException, ConstructorNotFoundException, NoSuchMethodException,
            IllegalAccessException, InvocationTargetException, InstantiationException;

}
