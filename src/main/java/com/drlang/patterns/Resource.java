package com.drlang.patterns;

public interface Resource<T> {

    T get();


    void set(T x);

}
