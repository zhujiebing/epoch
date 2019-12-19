package com.epoch.study.shengchanxiaofei;

/**
 * @description: synchronized版生产消费
 * @author: zjbing
 * @create: 2019-12-18 11:40
 **/
class Resource {

    private int num = 0;

    public synchronized void prod(){

        while (num != 0) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        num++;
        System.out.println(Thread.currentThread().getName()+"\t 生产一个蛋糕"+num);
        notifyAll();

    }

    public synchronized void  consum(){
        while (num != 1) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        num--;
        System.out.println(Thread.currentThread().getName()+"\t消费一个蛋糕"+num);

        notifyAll();
    }

}

public class SynchronizedCase {

    public static void main(String[] args) {
        Resource resource = new Resource();

        new Thread(()->{
            for (int i = 1; i <=10 ; i++) {
                resource.prod();
            }
        },"A").start();

        new Thread(()->{
            for (int i = 1; i <=10 ; i++) {
                resource.consum();
            }
        },"B").start();
    }
}
