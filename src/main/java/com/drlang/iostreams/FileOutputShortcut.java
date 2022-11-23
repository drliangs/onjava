package com.drlang.iostreams;

import java.io.*;

public class FileOutputShortcut {
    static String file = "FileOutputShortcut.dat";

    public static void main(String[] args) {
        try(BufferedReader in  =new BufferedReader(new StringReader(BufferedInputFile.read("src/main/java/com/drlang/iostreams/FileOutputShortcut.java")))) {
            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(file)));
            in.lines().forEach(out::write);
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println(BufferedInputFile.read(file));
    }
}
