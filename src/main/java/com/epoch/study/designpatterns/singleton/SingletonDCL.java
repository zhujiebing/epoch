package com.epoch.study.designpatterns.singleton;

public class SingletonDCL {

    private volatile static  SingletonDCL instance;

    private SingletonDCL() {
        System.out.println("==============");
    }

    public static SingletonDCL getInstance (){
        // DCL 双端检锁机制，会存在指令重排问题，必须加上volatile
        if (instance == null ) {
            synchronized (SingletonDCL.class) {
                if (instance == null) {
                    instance = new SingletonDCL();
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) {
    }

}
