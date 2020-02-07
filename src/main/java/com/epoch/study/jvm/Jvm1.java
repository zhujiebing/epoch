package com.epoch.study.jvm;

/**
 * @description: 调用常量类不会进行类初始化
 * @author: zjbing
 * @create: 2020-02-07 15:52
 **/

class ConstantClass {

    public ConstantClass(){
        System.out.println("构造器");
    }

    static {
        System.out.println("静态构造块");
    }

    {
        System.out.println("普通构造块");
    }

    public static final String str = "hello world";
}

public class Jvm1 {

    public static void main(String[] args) {
        System.out.println(ConstantClass.str);
    }
}
