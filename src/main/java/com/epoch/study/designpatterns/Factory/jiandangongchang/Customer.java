package com.epoch.study.designpatterns.Factory.jiandangongchang;

/**
 * @description:   简单工厂又可以称为静态工厂【违反开闭原则，增加产品需要修改已有的代码】
 * @author: zjbing
 * @create: 2019-12-18 14:25
 **/
public class Customer {

    public static void main(String[] args) {

        BMW bmw = SimpleBMWFactory.create(1);
        bmw.desc();
    }
}
