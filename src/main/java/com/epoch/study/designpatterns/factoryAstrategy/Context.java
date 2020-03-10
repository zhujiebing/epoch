package com.epoch.study.designpatterns.factoryAstrategy;

public class Context {
 
    private Strategy strategy;
   
    public Double calRecharge(Double charge, Integer type) {
       /*strategy = StrategyFactory.getInstance().creator(type);*/
        strategy = StrategyFactory.getInstance(type);
       return strategy.calRecharge(charge, RechargeTypeEnum.valueOf(type));
    }
 
    /*public Strategy getStrategy() {
       return strategy;
    }
 
    public void setStrategy(Strategy strategy) {
       this.strategy = strategy;
    }*/
   
}