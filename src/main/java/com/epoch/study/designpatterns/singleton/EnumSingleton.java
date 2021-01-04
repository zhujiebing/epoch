package com.epoch.study.designpatterns.singleton;

/**
 * @description: 枚举式单例
 * @author: zjbing
 * @create: 2020-06-10 16:00
 **/
public class EnumSingleton {

    private EnumSingleton(){}

    /**
     * 枚举类型是线程安全的，并且只会装载一次
     */
    private enum EnumSingle {

        INSTANCE;

        private final EnumSingleton instance;

        EnumSingle(){
            instance = new EnumSingleton();
        };

        private EnumSingleton getInstance(){
            return instance;
        }
    }

    public static EnumSingleton getInstance() {
        return EnumSingle.INSTANCE.getInstance();
    }
}
