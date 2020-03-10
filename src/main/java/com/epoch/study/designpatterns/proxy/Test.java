package com.epoch.study.designpatterns.proxy;

import java.lang.reflect.Proxy;

public class Test {

    public static void main(String[] args) {

        FenJiu fenJiu = new FenJiu();
        GuitaiInvocationHandle guitaiInvocationHandle = new GuitaiInvocationHandle(fenJiu);

        SellWine sellWine = (SellWine) Proxy.newProxyInstance(FenJiu.class.getClassLoader(),FenJiu.class.getInterfaces(),guitaiInvocationHandle);
        sellWine.sellJIu();

        System.out.println("================");


        Maotai maotai = new Maotai();
        GuitaiInvocationHandle guitaiInvocationHandle1 = new GuitaiInvocationHandle(maotai);

        SellWine sellWine1 = (SellWine)Proxy.newProxyInstance(maotai.getClass().getClassLoader(),Maotai.class.getInterfaces(),guitaiInvocationHandle1);
        sellWine1.sellJIu();;
    }
}
