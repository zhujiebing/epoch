package com.epoch.study.designpatterns.Factory.gongchangfangfa;

/**
 * @description:
 * @author: zjbing
 * @create: 2019-12-18 13:54
 **/
public class FactoryBMW320 implements FactoryBMW{
    @Override
    public BMW createBMW() {
        return new BMW320();
    }
}
