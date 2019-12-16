package com.epoch.study.jmm;

import java.util.concurrent.atomic.AtomicInteger;

public class CasDemo {

    public static void main(String[] args) {
         AtomicInteger atomicInteger = new AtomicInteger(5);

        System.out.println(atomicInteger.compareAndSet(5,1024) + "\tcurrent num = "+ atomicInteger.get());
        System.out.println(atomicInteger.compareAndSet(5,2019) + "\tcurrent num = "+ atomicInteger.get());

        // 结果：true	current num = 1024
        //      false	current num = 1024
    }
}
