package com.drlang.iostreams;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.stream.Stream;

public class ChannelCopy {
    private static final int BSIZE = 1024;

    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("arguments: sourcefile destfile");
            System.exit(1);
        }
        try (FileChannel in = new FileInputStream(args[0]).getChannel();
             FileChannel out = new FileOutputStream(args[1]).getChannel()) {
            ByteBuffer buffer = ByteBuffer.allocate(BSIZE);
            while (in.read(buffer) != -1) {
                buffer.flip();
                out.write(buffer);
                buffer.clear();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
