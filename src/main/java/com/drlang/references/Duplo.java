package com.drlang.references;

public class Duplo implements Cloneable {
    private int n;

    Duplo(int n) {
        this.n = n;
    }

    @Override
    public Duplo clone() {
        try {
            return (Duplo) super.clone();
        } catch (CloneNotSupportedException exception) {
            throw new RuntimeException(exception);
        }
    }

    public int getValue() {
        return n;
    }

    public void setValue(int n) {
        this.n = n;
    }

    public void increment() {
        n++;
    }

    @Override
    public String toString() {
        return Integer.toString(n);
    }

    public static class LocalCopy {
        public static Duplo g(Duplo v) {
            v.increment();
            return v;
        }

        public static Duplo f(Duplo v) {
            v = v.clone();
            v.increment();
            return v;
        }


    }

    public static void main(String[] args) {
        Duplo a = new Duplo(11);
        Duplo b = LocalCopy.g(a);
        System.out.println("a == b:" + (a == b) + "\na=" + a + "\nb=" + b);
        Duplo c = new Duplo(47);
        Duplo d = LocalCopy.f(c);
        System.out.println("c==d" + (c == d) + "\nc=" + c + "\nd=" + d);
    }

}
