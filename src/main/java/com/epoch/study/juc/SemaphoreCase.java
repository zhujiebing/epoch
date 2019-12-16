package com.epoch.study.juc;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

// 信号灯（量）
public class SemaphoreCase {

    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);// 模拟三个车位

        for (int i = 1; i <=6 ; i++) {
            new Thread(() -> {
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName()+"\t抢到车位");

                    TimeUnit.SECONDS.sleep(3);
                    System.out.println(Thread.currentThread().getName()+"\t三秒后离开车位");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    semaphore.release();
                }
            },String.valueOf(i)).start();
        }
    }
}
