package com.drlang.iostreams;

import java.io.*;

public class Redirecting {
    public static void main(String[] args) throws IOException {
        PrintStream console = System.out;
        try (BufferedInputStream in = new BufferedInputStream(new FileInputStream("src/main/java/com/drlang/iostreams/Redirecting.java"));
             PrintStream out = new PrintStream(new BufferedOutputStream(new FileOutputStream("Redirecting.txt")))) {
            System.setIn(in);
            System.setOut(out);
            System.setErr(out);
            new BufferedReader(new InputStreamReader(System.in))
                    .lines().forEach(System.out::println);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            System.setOut(console);
            while (System.in.available() != 0) {
                System.out.println((char) System.in.read());
            }
        }
    }
}
