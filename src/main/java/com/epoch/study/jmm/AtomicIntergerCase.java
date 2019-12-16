package com.epoch.study.jmm;

import java.util.concurrent.atomic.AtomicInteger;

class MyData3{
     AtomicInteger atomicInterger = new AtomicInteger();

    public void increm(){
        atomicInterger.getAndIncrement();
    }
}
// Atomic实现原子性
public class AtomicIntergerCase {
    public static void main(String[] args) {
        MyData3 myData3 = new MyData3();

        // 正常情况下num会修改为20000
        for (int i = 1; i <=20 ; i++) {
            new Thread(()->{
                for (int j = 1; j <=1000 ; j++) {
                    myData3.increm();
                }
            },String.valueOf(i)).start();
        }

        // 默认后台有一个main线程和一个Gc线程
        while (Thread.activeCount() > 2) {
            Thread.yield();
        }
        // 控制台会出现的打印： main	 当前num:20000     保证了原子性操作
        System.out.println(Thread.currentThread().getName()+"\t 当前num:" + myData3.atomicInterger);
    }
}
