package com.drlang.lowlevel;

import java.util.SplittableRandom;
import java.util.concurrent.PriorityBlockingQueue;

public class Consumer implements Runnable{
    private PriorityBlockingQueue<Prioritized> q;
    private SplittableRandom rand = new SplittableRandom(47);

    public Consumer(PriorityBlockingQueue<Prioritized> q) {
        this.q = q;
    }

    @Override
    public void run() {
        while (true){

            try {
                Prioritized pt = q.take();
                System.out.println(pt);
                if (pt instanceof Prioritized.EndSentinel){
                    pt.displaySequence();
                    break;
                }
                Thread.sleep((long) (rand.nextDouble() /10));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
