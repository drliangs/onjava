package com.drlang.references;

import java.util.concurrent.CopyOnWriteArrayList;

public class Snake implements Cloneable {
    private Snake next;
    private char c;

    public Snake(int i, char x) {
        c = x;
        if (--i > 0) {
            next = new Snake(i, (char) (x + 1));
        }
    }

    public void increment() {
        c++;
        if (next != null) next.increment();
    }

    @Override
    public String toString() {
        String s = ":" + c;
        if (next != null) s += next.toString();
        return s;
    }

    @Override
    public Snake clone() throws CloneNotSupportedException {
        try {
            Snake d = null;
            if (next != null) {
                Snake clone = next.clone();
                d = (Snake) super.clone();
                d.next = clone;
            }
            return d;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws Exception {
        Snake s = new Snake(5, 'a');
        s.increment();
        System.out.println("s = " + s);
        Snake s2 = s.clone();
        System.out.println(" s2 = " + s2);
        s2.increment();
        s.increment();
        System.out.println("after s.increment ,s2 =" + s2);
        System.out.println(s);
        System.out.println(s2);
    }
}
