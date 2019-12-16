package com.epoch.study.jmm;

class MyData1 { // Mydata1.java-->MyData1.class-->JVM字节码
    public volatile int num = 0;

    void incre(){
        num++;
    }
}
// volotile不保证原子性case
public class AtomicVolatile {

    public static void main(String[] args) {
        MyData1 myData1 = new MyData1();

        // 正常情况下num会修改为20000
        for (int i = 1; i <=20 ; i++) {
            new Thread(()->{
                for (int j = 1; j <=1000 ; j++) {
                    myData1.incre();
                }
            },String.valueOf(i)).start();
        }

        // 默认后台有一个main线程和一个Gc线程
        while (Thread.activeCount() > 2) {
            Thread.yield();
        }
        // 控制台会出现的打印： main	 当前num:19865
        System.out.println(Thread.currentThread().getName()+"\t 当前num:" + myData1.num);
    }
}
