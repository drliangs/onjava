package com.drlang.lowlevel;

import java.util.Random;
import java.util.concurrent.DelayQueue;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DelayQueueDemo {


    public static void main(String[] args) throws InterruptedException {
        DelayQueue<DelayedTask> queue = Stream.concat(new Random(47).ints(20, 0, 4000)
                        .mapToObj(DelayedTask::new), Stream.of(new DelayedTask.EndTask(4000)))
                .collect(Collectors.toCollection(DelayQueue::new));
        if (queue.size() > 0){
            queue.take().run();
        }
    }

}
