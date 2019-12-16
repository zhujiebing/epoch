package com.epoch.study.designpatterns.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class GuitaiInvocationHandle implements InvocationHandler {

    private Object pingpai;

    public GuitaiInvocationHandle(Object pingpai) {
        this.pingpai=pingpai;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("sell start:"+this.getClass().getSimpleName());
        method.invoke(pingpai,args);
        System.out.println("sell stop");
        return null;
    }
}
