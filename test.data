package com.drlang.iostreams;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;

public class TransferTo {
    public static void main(String[] args) {
        if (args.length !=2){
            System.out.println("arguments: sourcefile destfile");
            System.exit(1);
        }
        try (FileChannel in = new FileInputStream(args[0]).getChannel();
             FileChannel out = new FileOutputStream(args[1]).getChannel()){
            out.transferFrom(in,0,in.size());
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
