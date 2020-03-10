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
        //100 + 99 +sum(98)
        //100 + 99 + 98 +sum(97)
        //100 + 99 + 98 + 97 +sum(96)
        //100 + 99 + 98 + 97 + 96 +sum(95)
        // .......
        // 100 + 99 + ....  + 2 + sum(1)
        // sum(1) = 1
        // 最后结果是 100 + .... + 99 + 2 + 1
    }
}
