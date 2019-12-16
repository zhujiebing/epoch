package com.epoch.study.designpatterns.singleton;

public class SingletonDCL {

    private volatile static  SingletonDCL instance = null;

    private SingletonDCL() {
        System.out.println("==============");
    }

    private synchronized static SingletonDCL getInstance (){
        if (instance == null ) {
            synchronized (SingletonDCL.class) {
                if (instance == null) {
                    return new SingletonDCL();
                }
            }
        }
        return instance;
    }


}
