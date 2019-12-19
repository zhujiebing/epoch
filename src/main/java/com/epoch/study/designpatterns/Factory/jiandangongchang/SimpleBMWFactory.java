package com.epoch.study.designpatterns.Factory.jiandangongchang;

/**
 * @description:  工厂类角色：这是本模式的核心，含有一定的商业逻辑和判断逻辑，用来创建产品
 * @author: zjbing
 * @create: 2019-12-18 14:23
 **/
public class SimpleBMWFactory {

    public static BMW create(int type) {
        switch (type) {
            case 1:
                return new BMW520();
            default:
                return new BMW523();
        }
    }
}
