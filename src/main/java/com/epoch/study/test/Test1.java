package com.epoch.study.test;

class Test2 {
    public Test2(){
        System.out.println("test2构造方法");
    }

    static {
        System.out.println("test2静态构造块");
    }

    {
        System.out.println("test2普通构造块");
    }

    public void process (){
        System.out.println("22222");
    }
}
public class Test1 {

    public Test1(){
        System.out.println("test1构造方法");
    }

    static {
        System.out.println("test1静态构造块");
    }

    {
        System.out.println("test1普通构造块");
    }

    public void process (){
        System.out.println("111111");
    }

    public static void main(String[] args) {
        Test1 test1 = new Test1();
        test1.process();

        Test2 test2 = new Test2();
        test2.process();
    }
}
