package com.epoch.study.juc;

import java.util.concurrent.CountDownLatch;

// 计数
public class CountDownLatchCase {

    public static void main(String[] args) {

        CountDownLatch countDownLatch = new java.util.concurrent.CountDownLatch(10);

        for (int i = 1; i <=10 ; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"\t走人");
                countDownLatch.countDown();
            },String.valueOf(i)).start();

        }

        try {
            // 当计数到达0的时候才会执行
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+"\t主线程走人");
    }
}
