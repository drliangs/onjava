package com.drlang.patterns;

public class Proxy implements ProxyBase {
    private final ProxyBase implementation = new Implementation();

    @Override
    public void f() {
        implementation.f();
    }

    @Override
    public void g() {
        implementation.g();
    }

    @Override
    public void h() {
        implementation.h();
    }
    private static class ProxyDemo{
        public static void main(String[] args) {
            Proxy p = new Proxy();
            p.f();
            p.g();
            p.h();
        }
    }
}
