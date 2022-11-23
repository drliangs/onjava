package com.drlang.iostreams;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class GetChannel {
    private static String name = "data.txt";
    private static final int BSIZE = 1024;

    public static void main(String[] args) {
        try (FileChannel fc = new FileOutputStream(name).getChannel()){
                fc.write(ByteBuffer.wrap("Some.txt  ".getBytes()));
        }catch (Exception e){
            throw new RuntimeException(e);
        }
        try (FileChannel fc = new RandomAccessFile(name,"rw").getChannel()){
            fc.position(fc.size());
            fc.write(ByteBuffer.wrap("Some more".getBytes()));
        }catch (Exception e){
            throw new RuntimeException(e);
        }
        try (FileChannel fc = new FileInputStream(name).getChannel()){
            ByteBuffer buff = ByteBuffer.allocate(BSIZE);
            fc.read(buff);
            buff.flip();
            while (buff.hasRemaining()){
                System.out.print((char) buff.get());
            }
        }catch (Exception e){
            throw new RuntimeException(e);
        }
        System.out.flush();
    }
}
