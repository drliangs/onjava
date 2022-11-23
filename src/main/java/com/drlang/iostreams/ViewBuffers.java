package com.drlang.iostreams;

import java.nio.*;

public class ViewBuffers {
    public static void main(String[] args) {
        ByteBuffer bb = ByteBuffer.wrap(new byte[]{0, 0, 0, 0, 0, 0, 0, 'a',});
        bb.order(ByteOrder.LITTLE_ENDIAN);
        bb.rewind();
        System.out.print("Byte Buffer  ");
        while (bb.hasRemaining()) {
            System.out.print(bb.position() + " -> " + bb.get() + ",  ");
        }
        System.out.println();
        CharBuffer cb = bb.rewind().asCharBuffer();
        System.out.print("Char Buffer  ");
        while (cb.hasRemaining()) {
            System.out.print(cb.position() + " -> " + cb.get() + ",  ");
        }
        System.out.println();
        FloatBuffer fb = bb.rewind().asFloatBuffer();
        System.out.print("Float Buffer  ");

        while (fb.hasRemaining()) {
            System.out.print(fb.position() + " -> " + fb.get() + ",  ");
        }
        System.out.println();
        IntBuffer ib = bb.rewind().asIntBuffer();
        System.out.print("Int Buffer  ");
        while (ib.hasRemaining()) {
            System.out.print(ib.position() + " -> " + ib.get() + ",  ");
        }
        System.out.println();
        LongBuffer lb = bb.rewind().asLongBuffer();
        System.out.print("Long Buffer  ");
        while (lb.hasRemaining()) {
            System.out.print(lb.position() + " -> " + lb.get() + ",  ");
        }
        System.out.println();
        ShortBuffer sb = bb.rewind().asShortBuffer();
        System.out.print("Short Buffer  ");
        while (sb.hasRemaining()) {
            System.out.print(sb.position() + " -> " + sb.get() + ",  ");
        }
        System.out.println();
        DoubleBuffer db = bb.rewind().asDoubleBuffer();
        System.out.print("Double Buffer  ");
        while (db.hasRemaining()){
            System.out.print(db.position() + " -> " + db.get() + ",  ");

        }
        System.out.println();
        ByteOrder byteOrder = ByteOrder.nativeOrder();
        System.out.println(byteOrder.toString());

    }


}
