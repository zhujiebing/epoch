package com.epoch.study.jmm;

class MyData {
    //static int num = 0;
    public volatile int num = 0;

    void addNum (){
        this.num =  60;
    }
}
public class Volatile {

    public static void main(String[] args) {
        MyData myData = new MyData();

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\t come in");
            myData.addNum();
            System.out.println(Thread.currentThread().getName() + "\t update num: "+ myData.num);
        },"AAA").start();

        while (myData.num == 0) {
            // 等待num被修改 // 如果不加volatile，程序会一直在这里转
        }
        System.out.println(Thread.currentThread().getName()+"\t 当前num：" + myData.num);
    }
}
