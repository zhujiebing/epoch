package com.epoch.study.designpatterns.strategy;

/**
 * @description:
 * @author: zjbing
 * @create: 2019-12-20 14:24
 **/
public class ConcreteStrategyB implements Strategy{
    @Override
    public void algorithmLogic() {
        System.out.println("=====策略B====");
    }
}
