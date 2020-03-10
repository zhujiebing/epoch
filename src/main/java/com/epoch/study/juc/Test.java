package com.epoch.study.juc;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @description:
 * @author: zjbing
 * @create: 2020-03-02 16:26
 **/
public class Test {




        public static void main(String[] args ){

            final Semaphore semaphore = new Semaphore(1);
            AtomicInteger atomicInterger = new AtomicInteger(0);
            for (int i = 0; i < 200; i++ ){

                //final int finalI = i;


                new Thread(new Runnable() {
                    public void run() {
                        try {
                            semaphore.acquire(atomicInterger.get());
                            System.out.println(atomicInterger.get());
                            semaphore.release(atomicInterger.getAndIncrement());
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        }
}
