package com.epoch.study.juc;


import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @description:
 * @author: zjbing
 * @create: 2020-03-02 15:40
 **/
public class LockCondition {
    public static void main(String[] args) {
        Resource resource = new Resource();

        new Thread(()->{
            resource.process1();
        },"1").start();

        new Thread(()->{
            resource.process2();
        },"2").start();

        new Thread(()->{
            resource.process3();
        },"3").start();

        new Thread(()->{
            resource.process4();
        },"4").start();

        new Thread(()->{
            resource.process5();
        },"5").start();
    }
}


class Resource{

    AtomicInteger atomicInterger = new AtomicInteger(1);

    private Lock lock = new ReentrantLock();

    private Condition condition = lock.newCondition();
    private Condition condition2 = lock.newCondition();
    private Condition condition3 = lock.newCondition();
    private Condition condition4 = lock.newCondition();
    private Condition condition5 = lock.newCondition();

    public void process1() {
        lock.lock();
        try {
            while (atomicInterger.get() != 1) {
                condition.await();
            }
            System.out.println("线程"+Thread.currentThread().getName()+"打印"+atomicInterger.get());
            //通知
            atomicInterger.getAndIncrement();
            condition2.signal();
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public void process2() {
        lock.lock();
        try {
            while (atomicInterger.get() != 2) {
                condition2.await();
            }
            System.out.println("线程"+Thread.currentThread().getName()+"打印"+atomicInterger.get());
            //通知
            atomicInterger.getAndIncrement();
            condition3.signal();
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public void process3() {
        lock.lock();
        try {
            while (atomicInterger.get() != 3) {
                condition3.await();
            }
            System.out.println("线程"+Thread.currentThread().getName()+"打印"+atomicInterger.get());
            //通知
            atomicInterger.getAndIncrement();
            condition4.signal();
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public void process4() {
        lock.lock();
        try {
            //判断
            while (atomicInterger.get() != 4) {
                condition4.await();
            }
            // 干活
            System.out.println("线程"+Thread.currentThread().getName()+"打印"+atomicInterger.get());
            //通知
            atomicInterger.getAndIncrement();
            condition5.signal();
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public void process5() {
        lock.lock();
        try {
            //判断
            while (atomicInterger.get() != 5) {
                condition5.await();
            }
            // 干活
            System.out.println("线程"+Thread.currentThread().getName()+"打印"+atomicInterger.get());
            //通知
            atomicInterger.getAndIncrement();
            condition.signal();
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}

