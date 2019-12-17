package com.epoch.study.deadlock;
//创建两个线程类
class T1 implements Runnable {
    Object o1;
    Object o2;

    public T1(Object o1, Object o2) {
        this.o1 = o1;
        this.o2 = o2;
    }

    public void run() {
        //锁o1和o2
        synchronized (o1) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            synchronized (o2) {
                System.out.println("o2");
            }
        }
    }
}

class T2 implements Runnable {
    Object o1;
    Object o2;

    public T2(Object o1, Object o2) {
        this.o1 = o1;
        this.o2 = o2;
    }

    public void run() {
        synchronized (o2) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            synchronized (o1) {
                System.out.println("o1");
            }
        }

    }
}
/**
 * @description:
 * @author: zjbing
 * @create: 2019-12-17 18:03
 **/
public class DeadLockCase2 {

    public static void main(String[] args) {
        /**
         * 创建并启动两个线程t1、t2。两个线程都要共享o1、o2两个对象
         */
        Object o1 = new Object();
        Object o2 = new Object();
        Thread t1 = new Thread(new T1(o1, o2));
        Thread t2 = new Thread(new T2(o1, o2));
        t1.start();
        t2.start();
    }



}
