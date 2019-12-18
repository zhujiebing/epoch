package com.epoch.study.deadlock;

import java.util.concurrent.TimeUnit;

/**
 * @description:  线程操作资源类写法
 * @author: zjbing
 * @create: 2019-12-17 18:03
 **/

class HoldLockThread implements Runnable{

    private String lockA;

    private String lockB;

    public HoldLockThread(String lockA, String lockB) {
        this.lockA = lockA;
        this.lockB = lockB;
    }

    @Override
    public void run() {
        synchronized (lockA) {
            System.out.println(Thread.currentThread().getName()+"\t 持有锁"+lockA+"，尝试获取锁"+lockB);

            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            synchronized (lockB) {
                System.out.println(Thread.currentThread().getName()+"\t持有"+lockB+",尝试获取"+lockA);
            }
        }
    }
}
public class DeadLockCase2 {

    public static void main(String[] args) {

         String lockA="lockA";
         String lockB="lockB";

        new Thread(new HoldLockThread(lockA,lockB),"A").start();
        new Thread(new HoldLockThread(lockB,lockA),"B").start();


    }



}
