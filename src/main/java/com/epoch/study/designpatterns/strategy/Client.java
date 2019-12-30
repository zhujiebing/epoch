package com.epoch.study.designpatterns.strategy;

/**
 * @description:
 * @author: zjbing
 * @create: 2019-12-20 14:26
 **/
public class Client {

    public static void main(String[] args) {
        Context context = new Context(new ConcreteStrategyB());
        context.contextInterface();
    }
}
