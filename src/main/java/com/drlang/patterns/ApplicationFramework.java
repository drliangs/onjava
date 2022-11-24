package com.drlang.patterns;

import java.util.stream.IntStream;

public abstract class ApplicationFramework {
    public ApplicationFramework() {
        templateMethod();
    }

    abstract void customize1(int n);

    abstract void customize2(int n);

    private void templateMethod() {
        IntStream.range(0, 5)
                .forEach(n -> {
                    customize1(n);
                    customize2(n);
                });
    }

    private static class MyApp extends ApplicationFramework {
        public MyApp() {
            super();
        }

        @Override
        void customize1(int n) {
            System.out.println("customize1 " + n);
        }

        @Override
        void customize2(int n) {
            System.out.println("customize2 " + n);

        }
    }

    public static void main(String[] args) {
        new MyApp();
    }

}
