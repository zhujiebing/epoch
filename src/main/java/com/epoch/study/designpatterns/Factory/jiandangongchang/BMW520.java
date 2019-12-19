package com.epoch.study.designpatterns.Factory.jiandangongchang;

/**
 * @description: 具体产品角色：工厂类所创建的对象就是此角色的实例。在java中由一个具体类实现。
 * @author: zjbing
 * @create: 2019-12-18 14:21
 **/
public class BMW520 extends BMW{

    public BMW520() {
        System.out.println("520产品线");
    }

    @Override
    void desc() {
        System.out.println("520高级车");
    }
}
