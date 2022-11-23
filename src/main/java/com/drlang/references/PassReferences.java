package com.drlang.references;

public class PassReferences {
    public static void f(PassReferences h){
        System.out.println("h inside f():" + h);
    }

    public static void main(String[] args) {
        PassReferences passReferences = new PassReferences();
        System.out.println("p inside main():" + passReferences);
        f(passReferences);
    }
}
