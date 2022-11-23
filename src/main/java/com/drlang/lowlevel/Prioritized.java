package com.drlang.lowlevel;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class Prioritized implements Comparable<Prioritized> {
    private static AtomicInteger counter = new AtomicInteger();
    private final int id = counter.getAndIncrement();
    private final int priority;
    private static List<Prioritized> sequence = new CopyOnWriteArrayList<>();

    public Prioritized(int priority) {
        this.priority = priority;
        sequence.add(this);
    }

    @Override
    public int compareTo(Prioritized o) {
        return Integer.compare(o.priority, priority);
    }

    @Override
    public String toString() {
        return String.format("[%d] Prioritized %d", priority, id);
    }

    public void displaySequence() {
        int count = 0;
        for (Prioritized pt : sequence) {
            System.out.printf("(%d:%d)", pt.id, pt.priority);
            if (++count % 5==0){
                System.out.println();
            }
        }
    }
    public static class EndSentinel extends Prioritized{
        public EndSentinel() {
            super(-1);
        }
    }
}
