package com.drlang.patterns;

public class CommandPattern {
    public static void show(Command cmd){
        System.out.println(cmd.msg);
    }
    public static void main(String[] args) {
        show(new Command("First Command"));
        show(new Command("Second command"));
    }
}

class Command{
    public final String msg;

    public Command(String msg) {
        this.msg = msg;
    }
}
