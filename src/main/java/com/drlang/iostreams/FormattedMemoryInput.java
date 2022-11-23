package com.drlang.iostreams;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;

public class FormattedMemoryInput {
    public static void main(String[] args) {
        try (DataInputStream in = new DataInputStream(new ByteArrayInputStream
                (BufferedInputFile.read("src/main/java/com/drlang/iostreams/FormattedMemoryInput.java").
                        getBytes()))){
                while (true){
                    System.out.print((char) in.readByte());
                }


        }catch (Exception e){

        }
    }
}
