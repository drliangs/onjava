package com.drlang.patterns.chain;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Result {
    public final boolean success;
    public final List<Double> line;
    public Result(List<Double> line) {
        this.line = line;
        success = true;
    }
    private Result(){
        success =false;
        line = Collections.emptyList();
    }
    public static final Result fail = new Result();
}
