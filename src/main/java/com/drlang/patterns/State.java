package com.drlang.patterns;

public class State {
    private State implementation;
    protected State(){}

    public State(State imp){
        this.implementation = imp;
    }
    public void change(State newImp){
        implementation = newImp;
    }
    public void f(){
        implementation.f();
    }
    public void g(){
        implementation.g();
    }
    public void h(){
        implementation.h();
    }
    static class Implementation1 extends State{
        @Override
        public void f() {
            System.out.println("Implementation1.f()");
        }

        @Override
        public void g() {
            System.out.println("Implementation1.g()");

        }

        @Override
        public void h() {
            System.out.println("Implementation1.h()");
        }
    }
    static class Implementation2 extends State{
        @Override
        public void f() {
            System.out.println("Implementation2.f()");

        }

        @Override
        public void g() {
            System.out.println("Implementation2.g()");

        }

        @Override
        public void h() {
            System.out.println("Implementation2.h()");

        }
    }
    public static class StateDemo{
        static void test(State s){
            s.f();
            s.g();
            s.h();
        }

    }

    public static void main(String[] args) {
        State s = new State(new Implementation1());
        StateDemo.test(s);
        System.out.println("Changing implementation");
        s.change(new Implementation2());
        StateDemo.test(s);
    }
}
