package com.epoch.study.juc;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

// 做减法
public class CyclicBarrierCase {

    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7,() -> {
            System.out.println(Thread.currentThread().getName()+"\t召唤神龙");
        });

        for (int i = 1; i <=7 ; i++) {
            final int tempNum = i;
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName()+"\t收集第"+tempNum+"颗龙珠");

                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            },String.valueOf(i)).start();
        }
    }
}
