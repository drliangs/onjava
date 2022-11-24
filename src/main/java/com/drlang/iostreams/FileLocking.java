package com.drlang.iostreams;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.util.concurrent.TimeUnit;

public class FileLocking {
    public static void main(String[] args) {
        try (FileChannel fc = new FileOutputStream("file.txt").getChannel()) {
            FileLock fl = fc.tryLock();
            if (fl != null) {
                System.out.println("Locked file");
                TimeUnit.MILLISECONDS.sleep(100);
                fl.release();;
                System.out.println("Released Lock");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
