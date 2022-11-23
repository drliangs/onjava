package com.drlang.iostreams;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;

public class TestEOF {
    public static void main(String[] args) {
        try (DataInputStream in = new DataInputStream(new BufferedInputStream
                (new FileInputStream("classpath:primes.txt")))) {
            while (in.available() != 0) {
                System.out.print((char) in.readByte());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
