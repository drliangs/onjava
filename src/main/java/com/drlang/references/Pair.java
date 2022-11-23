package com.drlang.references;

import java.util.AbstractList;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Pair<K, V> {
    public final K key;
    public final V value;

    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K key() {
        return key;
    }

    public V value() {
        return value;
    }

    public static <K, V> Pair<K, V> make(K k, V v) {
        return new Pair<>(k, v);
    }

    static class Letters implements Supplier<Pair<Integer, String>> {
        private int number = 1;
        private char letter = 'A';

        @Override
        public Pair<Integer, String> get() {
            return new Pair<>(number++, "" + letter++);
        }
    }

    public static void main(String[] args) {
        Map<Integer, String> map = Stream.generate(new Letters())
                .limit(11)
                .collect(Collectors.toMap(Pair::key,
                        Pair::value));
        System.out.println(map);
    }


   static public class CountingIntegerList extends AbstractList<Integer> {
        private int size;

        public CountingIntegerList() {
        }

        public CountingIntegerList(int size) {
            this.size = Math.max(size, 0);
        }

        @Override
        public Integer get(int index) {
            return index;
        }

        @Override
        public int size() {
            return size;
        }

       public static void main(String[] args) {
           CountingIntegerList countingIntegerList = new CountingIntegerList(30);
           System.out.println(countingIntegerList);
           System.out.println(countingIntegerList.get(500));
       }
    }


}
