package com.epoch.study.designpatterns.singleton;

public class SingletonL {

    /**
     * 优点:解决线程安全，延迟初始化( Effective Java推荐写法)
     */
    private SingletonL(){
        System.out.println("====L====");
    }

    public static SingletonL getInstance () {
        return Holder.SINGLE_TON;
    }

    private static class Holder {
        private static final SingletonL SINGLE_TON = new SingletonL();
    }


    public static void main(String[] args) {
        System.out.println("=================");
        SingletonL.getInstance();
    }
}
