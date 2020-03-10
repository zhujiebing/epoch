package com.epoch.study.designpatterns.factoryAstrategy;

import java.util.HashMap;
import java.util.Map;

public class StrategyFactory {

    private static StrategyFactory factory = new StrategyFactory();

    private StrategyFactory() {
    }

    private static Map<Integer, Strategy> strategyMap = new HashMap<>();

    static {
        strategyMap.put(RechargeTypeEnum.E_BANK.value(), new EBankStrategy());
        strategyMap.put(RechargeTypeEnum.BUSI_ACCOUNTS.value(), new BusiAcctStrategy());
        strategyMap.put(RechargeTypeEnum.MOBILE.value(), new MobileStrategy());
    }

    /*public Strategy creator(Integer type) {
        return strategyMap.get(type);
    }

    public static StrategyFactory getInstance() {
        return factory;
    }*/

    public static Strategy getInstance(Integer type) {
        return strategyMap.get(type);
    }
}