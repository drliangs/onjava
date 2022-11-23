package com.drlang.lowlevel;

import java.util.Queue;
import java.util.SplittableRandom;
import java.util.concurrent.atomic.AtomicInteger;

public class Producer implements Runnable{
    private static AtomicInteger seed = new AtomicInteger(47);
    private SplittableRandom rand = new SplittableRandom(seed.getAndAdd(10));
    private Queue<Prioritized> queue;

    public Producer(Queue<Prioritized> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        rand.ints(10,0,20)
                .mapToObj(Prioritized::new)
                .peek(p-> {
                    try {
                        Thread.sleep((long) (rand.nextDouble() /10));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }).forEach(queue::add);
        queue.add(new Prioritized.EndSentinel());
    }
}
