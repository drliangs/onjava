package com.drlang.patterns.trash;

public interface Fillable<T extends Trash>  {
    void addTrash(T t);
}
