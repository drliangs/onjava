package com.drlang.iostreams;

import java.io.PrintStream;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;

public class IntBufferDemo {
    private static final int BSZIE = 1024;

    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(BSZIE);
        IntBuffer ib = buffer.asIntBuffer();
        ib.put(new int[]{11, 42, 47, 99, 143, 811, 1016});
        System.out.println(ib.get(3));
        ib.put(3, 1811);
        ib.flip();
        while (ib.hasRemaining()){
            int i = ib.get();
            System.out.println(i);
        }
    }
}
