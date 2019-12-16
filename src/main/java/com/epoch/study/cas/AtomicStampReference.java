package com.epoch.study.cas;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * ABA问题
 */
public class AtomicStampReference {

    //第一种情况会出现ABA
    private static AtomicReference<Integer> atomicReference = new AtomicReference<>(100);

    //第二种情况解决ABA问题
    private static AtomicStampedReference<Integer> atomicStampedReference = new AtomicStampedReference<Integer>(100,1);// 当前值1，当前版本号1

    public static void main(String[] args) {

        // 会出现ABA问题
        System.out.println("以前会出现ABA问题");
        new Thread(()->{
            atomicReference.compareAndSet(100,101);
            atomicReference.compareAndSet(101,100);
        },"t1").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(()->{
            atomicReference.compareAndSet(100, 2019);
            System.out.println("\t当前值："+atomicReference.get());
        },"t2").start();


        // 解决ABA问题
        try {TimeUnit.SECONDS.sleep(3);}catch (InterruptedException e) {e.printStackTrace();}
        System.out.println("以下解决ABA问题");

        new Thread(()->{
            int stamp = atomicStampedReference.getStamp();
            System.out.println(Thread.currentThread().getName()+"\t当前版本号:"+stamp);

            try {TimeUnit.SECONDS.sleep(1);}catch (InterruptedException e) {e.printStackTrace();}

            atomicStampedReference.compareAndSet(100,101,atomicStampedReference.getStamp(),atomicStampedReference.getStamp()+1);
            System.out.println(Thread.currentThread().getName()+"\t当前版本号:"+atomicStampedReference.getStamp());
            atomicStampedReference.compareAndSet(101,100,atomicStampedReference.getStamp(),atomicStampedReference.getStamp()+1);
            System.out.println(Thread.currentThread().getName()+"\t当前版本号:"+atomicStampedReference.getStamp());
        },"t3").start();


        new Thread(()->{
            int stamp = atomicStampedReference.getStamp();
            System.out.println(Thread.currentThread().getName()+"\t当前版本号:"+stamp);
            try {TimeUnit.SECONDS.sleep(3);}catch (InterruptedException e) {e.printStackTrace();}
            boolean result = atomicStampedReference.compareAndSet(100,2019,stamp,stamp+1);
            System.out.println(Thread.currentThread().getName()+"\t修改结果:"+result);
            System.out.println(Thread.currentThread().getName()+"\t当前版本号:"+atomicStampedReference.getStamp());
            System.out.println(Thread.currentThread().getName()+"\t当前值:"+atomicStampedReference.getReference());
        },"t4").start();
    }
}
