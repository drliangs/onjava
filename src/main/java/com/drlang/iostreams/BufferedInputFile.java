package com.drlang.iostreams;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.stream.Collectors;

public class BufferedInputFile {
    public static String read(String filename){
        try(BufferedReader in = new BufferedReader(new FileReader(filename))) {
            return in.lines().collect(Collectors.joining("\n"));
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        System.out.println(read("BufferedInputFile.java"));
    }
}
