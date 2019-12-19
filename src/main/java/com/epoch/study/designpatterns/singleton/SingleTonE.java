package com.epoch.study.designpatterns.singleton;

public class SingleTonE
{
    /**
     * 优点：没有线程安全问题，简单
     * 缺点：提前初始化会延长类加载器加载类的时间；如果不使用会浪费内存空间； 不能传递参数
     */
    private static final SingleTonE instance = new SingleTonE();

    private SingleTonE () {
        System.out.println("=======E===========");
    }

    public static SingleTonE getInstance() {
        return  instance;
    }

    public static void main(String[] args) {

        System.out.println("=================");
    }
}
