package com.epoch.study.designpatterns.Factory.gongchangfangfa;

/**
 * @description:
 * @author: zjbing
 * @create: 2019-12-18 13:55
 **/
public class FactoryBMW523 implements FactoryBMW{
    @Override
    public BMW createBMW() {
        return new BMW523();
    }
}
