package com.epoch.study.designpatterns.strategy;

/**
 * @description:
 * @author: zjbing
 * @create: 2019-12-20 14:22
 **/

public class Context {
    //	Context持有Strategy的引用，并且提供了调用策略的方法
    private Strategy strategy;

    public Context(Strategy strategy) {
        this.strategy = strategy;
    }


    public void contextInterface(){
        strategy.algorithmLogic();
    }
}
