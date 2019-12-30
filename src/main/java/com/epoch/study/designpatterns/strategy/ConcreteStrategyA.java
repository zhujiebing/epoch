package com.epoch.study.designpatterns.strategy;

/**
 * @description:
 * @author: zjbing
 * @create: 2019-12-20 14:23
 **/
public class ConcreteStrategyA implements Strategy{
    @Override
    public void algorithmLogic() {
        System.out.println("======策略A======");
    }
}
