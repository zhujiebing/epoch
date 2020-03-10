package com.epoch.study.jmm;

public class SingletonDemo {

    private static volatile SingletonDemo instance = null;

    private SingletonDemo(){
        System.out.println(Thread.currentThread().getName()+"\t 初始化" );
    }

    //加上synchronize可以解决同步问题。  public synchronized static SingletonDemo getInstance(){
    public static SingletonDemo getInstance(){
        // DCL 双端检锁机制，会存在指令重排问题，必须加上volatile
        if (instance == null) {
            synchronized (SingletonDemo.class) {
                if (instance == null) {
                    instance = new SingletonDemo();
                }
            }
        }
        return instance;
    }


    public static void main(String[] args) {
        // 单机版main线程操作动作
        /*System.out.println(SingletonDemo.getInstance() == SingletonDemo.getInstance());
        System.out.println(SingletonDemo.getInstance() == SingletonDemo.getInstance());
        System.out.println(SingletonDemo.getInstance() == SingletonDemo.getInstance());
        System.out.println(SingletonDemo.getInstance() == SingletonDemo.getInstance());*/

        // 并发多线程后，发生很大的变化
        for (int i = 1; i <=10 ; i++) {
            new Thread(()-> {
                SingletonDemo.getInstance();
            },String.valueOf(i)).start();
        }
    }
}
