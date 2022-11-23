package com.drlang.iostreams;

import java.io.*;

public class ShoringAndRecoveringData {
    public static void main(String[] args) {
        try (DataOutputStream outputStream = new DataOutputStream(new BufferedOutputStream(new FileOutputStream("Data.txt")))){
            outputStream.writeDouble(3.14159);
            outputStream.writeUTF("That was pi");
            outputStream.writeDouble(1.41413);
            outputStream.writeUTF("Square root of 2");
        }catch (Exception e){
            throw new RuntimeException(e);
        }
        try (DataInputStream in = new DataInputStream(new BufferedInputStream(new FileInputStream("Data.txt")))){
            System.out.println(in.readDouble());
            System.out.println(in.readUTF());
            System.out.println(in.readDouble());
            System.out.println(in.readUTF());
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
