package com.drlang.patterns;


import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

class FindMinima {
    protected Function<List<Double>, List<Double>> algorithm;
}

class LeastSquares extends FindMinima {
    LeastSquares() {
        algorithm = (line) -> Arrays.<Double>asList(1.1, 2.2);
    }
}

class Perturbation extends FindMinima {
    Perturbation() {
        algorithm = (line) -> Arrays.asList(3.3, 4.4);
    }
}

class Bisection extends FindMinima {
    public Bisection() {
        algorithm = (line) -> Arrays.asList(5.5, 6.6);
    }
}
class MinimaSolver{
    private FindMinima strategy;

    public MinimaSolver(FindMinima strategy) {
        this.strategy = strategy;
    }
    List<Double> minima(List<Double> line){
        return strategy.algorithm.apply(line);
    }
    void changeAlgorithm(FindMinima findMinima){
        this.strategy = findMinima;

    }
}

public class StrategyPattern {
    public static void main(String[] args) {
        MinimaSolver solver = new MinimaSolver(new LeastSquares());
        List<Double> line = Arrays.asList(1.0, 2.0, 1.0, 2.0, -1.0, 3.0, 4.0, 5.0, 4.0);
        System.out.println(solver.minima(line));
        solver.changeAlgorithm(new Perturbation());
        System.out.println(solver.minima(line));
        solver.changeAlgorithm(new Bisection());
        System.out.println(solver.minima(line));
    }
}
