package com.drlang.patterns;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class ShapeFactory2 implements FactoryMethod {
    private final Map<String, Constructor<?>> factories = new HashMap<>();

    private static Constructor<?> load(String id) {
        System.out.println("loading " + id);
        try {
            return Class.forName("com.drlang.patterns" + id, false, null).getConstructor();

        } catch (ClassNotFoundException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Shape create(String type) {
        try {
            return    (Shape)factories.computeIfAbsent(type,ShapeFactory2::load).newInstance();
        } catch (InstantiationException | InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        FactoryTest.test(new ShapeFactory2());
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
