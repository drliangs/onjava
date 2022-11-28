package com.drlang.patterns.trash;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class DynaFactory {
    private Map<String, Constructor<?>> constructorMap = new HashMap<>();
    private String packageName;

    public DynaFactory(String packageName) {
        this.packageName = packageName;
    }

    @SuppressWarnings("unchecked")
    public <T extends Trash> T create(TrashInfo info) {
        String typeName = "patterns." + packageName + "." + info.type;
        try {
            return (T) constructorMap.computeIfAbsent(typeName, this::findConstructor).newInstance(info.data);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException("Cannot create() Trash: " + info, e);
        }
    }

    private Constructor<?> findConstructor(String typeName) {
        System.out.println("Loading " + typeName);
        try {
        return     Class.forName(typeName)
                    .getConstructor(Double.class);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException("Ex");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Trash(Double) Constructor Not Found:" + typeName,e);
        }

    }
}
