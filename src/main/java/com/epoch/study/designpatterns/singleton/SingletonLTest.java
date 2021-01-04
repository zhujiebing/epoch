package com.epoch.study.designpatterns.singleton;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @description: 反射破坏单例
 * @author: zjbing
 * @create: 2020-06-10 15:03
 **/
public class SingletonLTest {

    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {

        Class clazz = SingletonL.class;
        Constructor c = clazz.getDeclaredConstructor(null);
        c.setAccessible(true);
        Object o = c.newInstance();
        Object o2 = c.newInstance();

        System.out.println(o == o2);
    }
}
