package com.epoch.study.shengchanxiaofei;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @description: lock版
 * @author: zjbing
 * @create: 2019-12-18 11:40
 **/

class Dangao{
    private int num = 0;

    private ReentrantLock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void prod(){
        lock.lock();

        try {
            while (num != 0) {
                condition.await();
            }

            num++;
            System.out.println(Thread.currentThread().getName()+"\t生产蛋糕"+num);
            condition.signalAll();

        }catch (Exception e) {

        }finally {
            lock.unlock();
        }
    }

    public void consum(){

        lock.lock();

        while (num!=1) {
            try {
                condition.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println(Thread.currentThread().getName()+"\t消费蛋糕"+num--+"剩余"+num);

        condition.signalAll();

    }
}

public class LockCase {

    public static void main(String[] args) {
        Dangao dangao = new Dangao();

        new Thread(()->{
            for (int i = 1; i <=10 ; i++) {
                dangao.prod();
            }
        },"A").start();

        new Thread(()->{
            for (int i = 1; i <=10 ; i++) {
                dangao.consum();
            }
        },"B").start();
    }
}
