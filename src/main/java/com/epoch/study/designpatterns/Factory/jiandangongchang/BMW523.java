package com.epoch.study.designpatterns.Factory.jiandangongchang;

/**
 * @description:具体产品角色：工厂类所创建的对象就是此角色的实例。在java中由一个具体类实现。
 * @author: zjbing
 * @create: 2019-12-18 14:22
 **/
public class BMW523 extends BMW{
    public BMW523() {
        System.out.println("523产品线");
    }

    @Override
    void desc() {
        System.out.println("523高级车");
    }
}
