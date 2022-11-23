package com.drlang.iostreams;

import java.io.RandomAccessFile;

public class UsingRandomAccessFile {
    static String file = "rtest.dat";

    public static void display() {
        try (RandomAccessFile rf = new RandomAccessFile(file, "r")) {
            for (int i = 0; i < 7; i++) {
                System.out.print("Value " + i + ": " + rf.readDouble());
                System.out.print("        " + rf.getFilePointer());
                System.out.println();
            }
            System.out.println(rf.readUTF());
            System.out.println(rf.getFilePointer());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        try (RandomAccessFile rf = new RandomAccessFile(file, "rw")) {
            for (int i = 0; i < 7; i++) {
                rf.writeDouble(i * 1.1414);
            }
            rf.writeUTF("The end of the file");
            rf.close();
            display();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        try (RandomAccessFile rf = new RandomAccessFile(file,"rw")){
                rf.seek(40);
//            System.out.println(rf.readDouble());
            rf.writeDouble(47.00001);
            System.out.println(rf.getFilePointer());
//            rf.close();
            rf.seek(40);
            System.out.println(rf.readDouble());
            display();
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
