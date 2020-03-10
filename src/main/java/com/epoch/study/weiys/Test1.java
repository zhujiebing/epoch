package com.epoch.study.weiys;

/**
 * @description:  & 是二进制运算  ，   上下组合：11为1,10为0,01为0
 * @author: zjbing
 * @create: 2020-03-06 14:55
 **/
public class Test1 {

    public static void main(String[] args) {
        int i = 3; //对应二进制 ...11
        int j = 2; //对应二进制 ...10
        int k = -3; //对应二进制 10000000 00000000 00000000 00000011(原码)

        //按位与& 从高位开始两个都为1 为1，否则 为0
        System.out.println(i & j); //10（2进制） -> 2（10进制）
        System.out.println(i & 0x1); //10（2进制） -> 2（10进制）
        System.out.println(i >> j);

        System.out.println((20 >> 4 & 0x1) == 1);
    }
}
