package com.drlang.patterns;

public final class IntegerSingleton implements Resource<Integer> {
    private static IntegerSingleton value = new IntegerSingleton();
    private Integer i = Integer.valueOf(0);

    private IntegerSingleton() {
        System.out.println("IntegerSingleton()");
    }

    public static IntegerSingleton instance() {
        return value;
    }

    @Override
    public Integer get() {
        return i;
    }

    @Override
    public void set(Integer x) {
        i = x;
    }

}

class SingletonPattern {
    public static <T> void show(Resource<T> r) {
        T val = r.get();
        System.out.println(val);
    }

    public static <T> void put(Resource<T> r, T val) {
        r.set(val);
    }

    public static void main(String[] args) {
        System.out.println("Inside main()");

        IntegerSingleton ir = IntegerSingleton.instance();
        IntegerSingleton ir2 = IntegerSingleton.instance();
        show(ir);
        put(ir2, 9);
        show(ir);
        System.out.println(ir == ir2);
    }
}
