package com.drlang.lowlevel;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class DelayedTask implements Runnable, Delayed {
    private static int countre = 0;
    private final int id = countre++;
    private final int delta;
    private final long trigger;
    protected static final List<DelayedTask> sequence = new ArrayList<>();


    public DelayedTask(int delayInMilliseconds) {
        this.delta = delayInMilliseconds;
        this.trigger = System.nanoTime() + TimeUnit.NANOSECONDS.convert(delta, TimeUnit.MILLISECONDS);
        sequence.add(this);
    }


    @Override
    public int compareTo(Delayed o) {
        DelayedTask that = (DelayedTask) o;
        return Long.compare(trigger, that.trigger);
    }

    @Override
    public void run() {
        System.out.println(this + "  ");
    }

    @Override
    public String toString() {
        return String.format("[%d] Task %d", delta, id);
    }

    public String summary() {
        return String.format("(%d:%d)", id, delta);
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(trigger - System.nanoTime(), TimeUnit.MILLISECONDS);
    }
    public static class EndTask extends DelayedTask{
        public EndTask(int delay){
            super(delay);
        }

        @Override
        public void run() {
            sequence.forEach(dt->{
                System.out.println(dt.summary());
            });
        }
    }
}
