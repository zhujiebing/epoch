package com.epoch.study.designpatterns.Factory.gongchangfangfa;

/**
 * @description:工厂方法模式
 *  工厂方法模式去掉了简单工厂模式中工厂方法的静态属性，使得它可以被子类继承。这样在简单工厂模式里集中
 * 在工厂方法上的压力可以由工厂方法模式里不同的工厂子类来分担。
 * 工厂方法模式组成：
 *  1)抽象工厂角色： 这是工厂方法模式的核心，它与应用程序无关。是具体工厂角色必须实现的接口或者必须继承的
 * 父类。在java中它由抽象类或者接口来实现。
 *  2)具体工厂角色：它含有和具体业务逻辑有关的代码。由应用程序调用以创建对应的具体产品的对象。
 *  3)抽象产品角色：它是具体产品继承的父类或者是实现的接口。在java中一般有抽象类或者接口来实现。
 *  4)具体产品角色：具体工厂角色所创建的对象就是此角色的实例。在java中由具体的类来实现。
 *  （开闭原则)当有新的产品产生时，只要按照抽象产品角色、抽象工厂角色提供的合同来生成，那么就可以被客户
 * 使用，而不必去修改任何已有 的代码。
 *
 * 缺点：  当需要增加的产品越来越多时，会定义很多工厂子类
 *
 * 开闭原则： 对扩展开放，对修改关闭。
 * @author: zjbing
 * @create: 2019-12-18 13:56
 **/
public class Customer {
    public static void main(String[] args) {
        FactoryBMW320 factoryBMW320 = new FactoryBMW320();
        factoryBMW320.createBMW();

        FactoryBMW523 factoryBMW523 = new FactoryBMW523();
        factoryBMW523.createBMW();
    }
}
