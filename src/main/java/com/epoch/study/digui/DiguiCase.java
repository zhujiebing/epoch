package com.epoch.study.digui;

public class DiguiCase {

    public static void main(String[] args) {
        System.out.println(sum(100));
    }

    /**
     * @param 求和方法
     */
    public static int sum(int num) {
        if (num == 1) {
            return 1;
        }
        return num + sum(num - 1);
        // 100 + sum(99)
        //99 + 100 +sum(98)
        //98 + 99 + 100 +sum(97)
        //97 + 98 + 99 + 100 +sum(96)
        //96 + 97 + 98 + 99 + 100 +sum(95)
        // .......
        // 2 + .... + 99 + 100 + sum(1)
        // sum(1) = 1
        // 最后结果是 2 + .... + 99 + 100 + 1
    }
}
