package com.drlang.iostreams;

import java.io.PrintWriter;

public class ChangeSystemOut {
    public static void main(String[] args) {
        PrintWriter printWriter = new PrintWriter(System.out,true);
        printWriter.println("Hello world");
    }
}
