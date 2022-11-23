package com.drlang.iostreams;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

public class MemoryInput {
    public static void main(String[] args) throws IOException {
        StringReader reader = new StringReader(BufferedInputFile.read("src/main/java/com/drlang/iostreams/MemoryInput.java"));
        int c;
        while ((c = reader.read()) != -1)
        {
            System.out.print((char) c);
        }
    }
}
