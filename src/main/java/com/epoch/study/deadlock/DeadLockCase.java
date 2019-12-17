package com.epoch.study.deadlock;


import java.util.concurrent.TimeUnit;

/**
 * @description: 死锁编码
 * @author: zjbing
 * @create: 2019-12-17 17:40
 **/
public class DeadLockCase {

    private String lockA = "lockA";

    private String lockB = "lockB";

    public static void main(String[] args) {
        DeadLockCase deadLockCase = new DeadLockCase();
        new Thread(()->{
            try {
                deadLockCase.processA();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"A").start();

        new Thread(()->{
            try {
                deadLockCase.processB();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"B").start();
    }

    private void processA() throws InterruptedException {
        synchronized (lockA) {
            System.out.println(Thread.currentThread().getName()+"\t获取锁A，尝试获取锁B");
            TimeUnit.SECONDS.sleep(1);

            synchronized (lockB) {
                System.out.println(Thread.currentThread().getName()+"\t尝试获取锁B");
            }
        }
    }

    private void processB() throws InterruptedException {
        synchronized (lockB) {
            System.out.println(Thread.currentThread().getName()+"\t获取锁B，尝试获取锁A");
            TimeUnit.SECONDS.sleep(1);

            synchronized (lockA) {
                System.out.println(Thread.currentThread().getName()+"\t尝试获取锁A");
            }
        }
    }
}
