package com.epoch.study.shengchanxiaofei;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @description: 阻塞队列版生产消费
 * @author: zjbing
 * @create: 2019-12-19 10:57
 **/

class MyResource{
    private volatile boolean flag = true;

    private BlockingQueue<String> blockingQueue = null;

    private AtomicInteger atomicInteger = new AtomicInteger();

    public MyResource(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    public void prod () throws InterruptedException {
        String data = null;
            while (flag) {

                data = atomicInteger.incrementAndGet()+"";
                boolean result = blockingQueue.offer(data,1, TimeUnit.SECONDS);
                TimeUnit.SECONDS.sleep(2);
                if (result) {
                    System.out.println(Thread.currentThread().getName()+"\t 生产了"+data);
                }else {
                    System.out.println();
                }
            }

    }

    public void consum() throws InterruptedException {
        while (flag) {
            String result = blockingQueue.poll(1,TimeUnit.SECONDS);
            TimeUnit.SECONDS.sleep(2);
            if (result == null || result.equalsIgnoreCase("")) {
                System.out.println(Thread.currentThread().getName()+"\t 没有取到蛋糕");
            }else {
                System.out.println(Thread.currentThread().getName()+"\t 取到"+result);
            }
        }
    }

    public void stop(){
        flag = false;
    }
}

public class ProdConsumBlockQueue {

    public static void main(String[] args) {
        MyResource resource = new MyResource(new ArrayBlockingQueue<String>(10));

        new Thread(()->{
            try {
                resource.prod();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"A").start();


        new Thread(()->{
            try {
                resource.consum();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"B").start();

        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        resource.stop();

        System.out.println("主线程说停止生产和消费");

    }
}
