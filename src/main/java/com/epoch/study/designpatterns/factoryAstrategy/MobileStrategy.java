package com.epoch.study.designpatterns.factoryAstrategy;

public class MobileStrategy implements Strategy {

    @Override
    public Double calRecharge(Double charge, RechargeTypeEnum type) {
       return charge;
    }
    
}