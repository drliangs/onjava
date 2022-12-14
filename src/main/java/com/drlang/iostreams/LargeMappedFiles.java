package com.drlang.iostreams;

import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class LargeMappedFiles {
    static int length = 0x8000000;

    public static void main(String[] args) {
        try (RandomAccessFile tdat = new RandomAccessFile("test.dat", "rw")) {
            MappedByteBuffer out = tdat.getChannel().map(FileChannel.MapMode.READ_WRITE, 0, length);
            for (int i = 0; i < length; i++) {
                out.put((byte) 'x');
            }
            System.out.println("Finished writing");
            for (int i = length/ 2 ;i < length /2 + 6 ; i++){
                System.out.println((char) out.get(i));
            }
        } catch (Exception e) {

        }
    }
}
