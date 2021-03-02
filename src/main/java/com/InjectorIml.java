package com;

import com.annotation.Inject;
import com.exception.ConstructorNotFoundException;
import com.exception.TooManyConstructorsException;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Тестовое задание
 * Задача: используя Java Reflection API и классы из пакета java.lang.reflect
 * реализовать простейшую версию Dependency Injection-контейнера.
 * Результатом работы должен стать Maven (или Gradle) проект, который может собираться в одну
 * или несколько JAR-библиотек.
 * Сторонние библиотеки использовать запрещено. Весь код должен быть написан вами. За исключением
 * библиотек для тестирования.
 * Для проверки работы библиотеки должны быть написаны Unit-тесты.
 * Задание разбито на несколько пунктов.
 * В сопроводительном письме, укажите:
 * - Какие пункты вы выполнили полностью
 * - А какие - частично
 * Задание целиком выполнять не обязательно.
 * <p>
 * Требования к реализации:
 * 1.  Запрещается использовать сторонние библиотеки (кроме юнит тестирования)
 * 2.  Реализацию необходимо осуществить в классе InjectorImpl на основе интерфейса Injector:
 * public interface Injector {
 * <T> Provider<T> getProvider(Class<T> type); //получение инстанса класса со всеми иньекциями
 * по классу интерфейса
 * <T> void bind(Class<T> intf, Class<? extends T> impl); //регистрация байндинга по классу
 * интерфейса и его реализации
 * <T> void bindSingleton(Class<T> intf, Class<? extends T> impl); //регистрация синглтон класса
 * }
 * Где
 * public interface Provider<T>
 * {
 * T getInstance();
 * }
 * 3. + Для осуществления байндинга в конструктор класса имплементации добавляется аннотация @Inject
 * Например:
 * pubic class  EventServiceImpl implements EventService {
 * private EventDao eventDao;
 *
 * @Inject public EventServiceImpl(EventDao eventDao) {
 * this.eventDao = eventDao;
 * }
 * }
 * 4. + Если в классе присутствует несколько конструкторов с аннотацией @Inject, выбрасывается
 * TooManyConstructorsException.
 * 5. + При отсутствии конструкторов с аннотацией Inject используется конструктор по умолчанию. При
 * отсутствии такового выбрасывается ConstructorNotFoundException.
 * 6. Если контейнер использует конструктор с аннотацией Inject и для какого-либо аргумента контейнер
 * не может найти binding, выбрасывается BindingNotFoundException.
 * 7. Если мы запрашиваем Provider для какого-либо класса и нет cоответствующего binding, возвращается null.
 * 8. Реализуйте возможность использования Singleton и Prototype бинов.
 * 9. Реализация singleton binding'ов должна быть ленивой (создание объекта при первом обращении).
 * 10. Реализация получения провайдеров должна быть потокобезопасной
 * 11. Поддержка field и method injection не требуется - Inject только через конструкторы.
 * 12. Все аргументы конструкторов гарантировано являются reference type'ами. То есть не предполагается
 * передача в конструкторы аргументов простых типов.
 * 13. Все конструкторы являются public
 * Задача:
 * - Создать интерфейсы Injector, Provider, необходимые классы исключений и аннотацию @Inject.
 * - Реализовать имплементацию InjectorImpl, которая:
 * 1. Сканирует конструкторы класса
 * 2. Сканирует аннотации @Inject конструкторов при байндинге
 * 3. Создающую инстансы классов и инжекцию параметров конструктора при обращении
 * (Singleton или Prototype).
 * - написать юнит тесты, покрывающие тест кейсы
 * <p>
 * Пример теста:
 * @Test void testExistingBinding()     {
 * Injector injector = new InjectorImpl(); //создаем имплементацию инжектора
 * injector.bind(EventDAO.class, InMemoryEventDAOImpl.class);
 * //добавляем в инжектор реализацию интерфейса
 * Provider<EventDAO> daoProvider = injector.getProvider(EventDAO.class);
 * //получаем инстанс класса из инжектора
 * assertNotNull(daoProvider);
 * assertNotNull(daoProvider.getInstance());
 * assertSame(InMemoryEventDAOImpl.class, daoProvider.getInstance().getClass());
 * }
 */

public class InjectorIml implements Injector {

    private Constructor<?> constructorWithAnnotation;

    private Object classObject;

    public InjectorIml() {
    }

    @Override
    public synchronized <T> Provider<T> getProvider(Class<T> type) {

        Field field = Arrays.stream(
                classObject
                        .getClass()
                        .getDeclaredFields())
                .filter(s -> s.getType() == type).findFirst().get();


        //  return (Provider<T>) field.getAnnotatedType().;
//        Field field = Arrays.stream(classObject.getClass().getDeclaredFields())
//                .filter(an -> an.getType() == type)
//                .findFirst().get();
//
//        return new Provider<Object> classObject();
        return null;
    }

    @Override
    public <T> void bind(Class<T> intf, Class<? extends T> impl)
            throws TooManyConstructorsException, ConstructorNotFoundException, NoSuchMethodException,
            IllegalAccessException, InvocationTargetException, InstantiationException {

        checkException(impl);

        classObject = constructorWithAnnotation.newInstance(intf.getDeclaredConstructor().newInstance());
    }

    @Override
    public <T> void bindSingleton(Class<T> intf, Class<? extends T> impl)
            throws TooManyConstructorsException, ConstructorNotFoundException, IllegalAccessException,
            InstantiationException {

        checkException(impl);
        classObject = Singleton.getInstance(intf);
    }

    private <T> void checkException(Class<? extends T> impl)
            throws TooManyConstructorsException, ConstructorNotFoundException {

        List<Constructor<?>> constructorList = Arrays.asList(impl.getDeclaredConstructors());

        List<Constructor<?>> constructorsWithAnnotation = constructorList
                .stream()
                .filter(constructor -> constructor.isAnnotationPresent(Inject.class))
                .collect(Collectors.toList());

        if (constructorsWithAnnotation.size() > 1) {
            throw new TooManyConstructorsException("More then one constructors");
        } else if (constructorsWithAnnotation.isEmpty() &
                constructorList
                        .stream()
                        .filter(s -> s.getParameterCount() == 0).findFirst()
                        .isEmpty()) {
            throw new ConstructorNotFoundException("default constructor not found");
        } else {
            if (constructorsWithAnnotation.stream().findFirst().isPresent()) {
                constructorWithAnnotation = constructorsWithAnnotation.stream().findFirst().get();
            }
        }
    }
}
