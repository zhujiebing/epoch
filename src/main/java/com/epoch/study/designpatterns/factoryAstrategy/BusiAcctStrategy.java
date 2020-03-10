package com.epoch.study.designpatterns.factoryAstrategy;

public class BusiAcctStrategy implements Strategy{

    @Override
    public Double calRecharge(Double charge, RechargeTypeEnum type) {
       return charge*0.90;
    }
    
} 