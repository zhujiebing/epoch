package com.epoch.study.test;

public class Son extends Father{

     public String nameA = "李四";

    public Son(){
        super();
        System.out.println("我是儿子");
    }

    public void getName (){
        System.out.println("子类："+nameA);
        System.out.println("父类："+super.nameA);
    }

    public static void main(String[] args) {
        Son son = new Son();
        son.getName();
    }
}
