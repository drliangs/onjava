package com.drlang.iostreams;

import java.io.*;
import java.nio.IntBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Arrays;

public class MappedIO {
    private static int numberInts = 4_000_000;
    private static int numOfUbuffInts = 100_000;

    private abstract static class Tester {
        private String name;

        public Tester(String name) {
            this.name = name;
        }

        public void runTest() {
            System.out.print(name + ":  ");
            long start = System.nanoTime();
            test();
            double duration = System.nanoTime() - start;
            System.out.format("%.3f%n", duration / 1.0e9);
        }

        public abstract void test();
    }

    private static Tester[] tests = {
            new Tester("Stream Write") {
                @Override
                public void test() {
                    try (DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(new File("temp.tmp"))))) {
                        for (int i = 0; i < numberInts; i++) {
                            dos.writeInt(i);
                        }
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            },
            new Tester("Mapped Write") {
                @Override
                public void test() {
                    try (FileChannel fc = new RandomAccessFile("temp.tmp", "rw").getChannel()) {
                        IntBuffer ib = fc.map(FileChannel.MapMode.READ_WRITE, 0, fc.size()).asIntBuffer();
                        for (int i = 0; i < numberInts; i++) {
                            ib.put(i);
                        }
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            },
            new Tester("Stream Read") {
                @Override
                public void test() {
                    try (DataInputStream dis = new DataInputStream(new BufferedInputStream(new FileInputStream("temp.tmp")))) {
                        for (int i = 0; i < numberInts; i++) {
                            dis.readInt();
                        }
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            },
            new Tester("Mapped Read") {
                @Override
                public void test() {
                    try (FileChannel fc = new FileInputStream("temp.tmp").getChannel()) {
                        IntBuffer ib = fc.map(FileChannel.MapMode.READ_ONLY, 0, fc.size()).asIntBuffer();
                        while (ib.hasRemaining()) {
                            ib.get();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            },
            new Tester("Stream Read/Write") {
                @Override
                public void test() {
                    try (RandomAccessFile raf = new RandomAccessFile(new File("temp.tmp"), "rw")) {
                        raf.writeInt(1);
                        for (int i = 0; i < numOfUbuffInts; i++) {
                            raf.seek(raf.length() - 4);
                            raf.writeInt(raf.readInt());
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            },
            new Tester("Mapped Read/Write") {
                @Override
                public void test() {
                    try (FileChannel fc = new RandomAccessFile(new File("temp.tmp"), "rw").getChannel()) {
                        IntBuffer ib = fc.map(FileChannel.MapMode.READ_WRITE, 0, fc.size()).asIntBuffer();
                        ib.put(0);
                        for (int i = 1; i < numOfUbuffInts; i++) {
                            ib.put(ib.get(i - 1));
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
    };

    public static void main(String[] args) {
        Arrays.stream(tests)
                .forEach(Tester::runTest);
    }
}
