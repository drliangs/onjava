package com.drlang.patterns;

public class Single<T> {
    private static Object single;

    public Single(T val) {
        if (single != null) {
            throw new RuntimeException("Attempt to reassign Single<" + val.getClass().getSimpleName() + ">");
        }
        single = val;
    }
    @SuppressWarnings("unchecked")
    public T get(){
        return (T) single;
    }
}
