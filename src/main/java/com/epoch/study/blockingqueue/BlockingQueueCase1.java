package com.epoch.study.blockingqueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class BlockingQueueCase1 {

    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);

        // 第一组： 异常型
        /*System.out.println(blockingQueue.add("a"));
        System.out.println(blockingQueue.add("b"));
        System.out.println(blockingQueue.add("c"));
        System.out.println(blockingQueue.add("d"));// Exception in thread "main" java.lang.IllegalStateException: Queue full

        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove()); //// java.util.NoSuchElementException*/


        // 第二组 特殊值
        /*System.out.println(blockingQueue.offer("a"));
        System.out.println(blockingQueue.offer("b"));
        System.out.println(blockingQueue.offer("c"));
        System.out.println(blockingQueue.offer("d")); // false

        System.out.println(blockingQueue.peek());

        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());// null*/


        // 第三组 阻塞组
        /*blockingQueue.put("a");
        blockingQueue.put("b");
        blockingQueue.put("c");
        blockingQueue.put("d");  // 阻塞

        System.out.println("======================");

        blockingQueue.take();
        blockingQueue.take();
        blockingQueue.take();
        blockingQueue.take();// 阻塞*/


        // 第四种 超时组
        blockingQueue.offer("a",2L, TimeUnit.SECONDS);
        blockingQueue.offer("b",2L, TimeUnit.SECONDS);
        blockingQueue.offer("c",2L, TimeUnit.SECONDS);
        blockingQueue.offer("d",2L, TimeUnit.SECONDS); // 阻塞两秒
    }
}
