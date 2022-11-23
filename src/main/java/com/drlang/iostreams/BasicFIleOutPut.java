package com.drlang.iostreams;

import java.io.*;

public class BasicFIleOutPut {
    static String file = "BasicFileOutput.dat";

    public static void main(String[] args) {
        try (BufferedReader in = new BufferedReader(new
                StringReader(BufferedInputFile.read("src/main/java/com/drlang/iostreams/BasicFIleOutPut.java")));
             PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(file)))
        ){
            in.lines().forEach(out::println);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
        System.out.println(BufferedInputFile.read(file));
    }
}
