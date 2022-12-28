package com.fci.fawrysystem.controllers.discount;

import com.fci.fawrysystem.models.account.IAccount;

public class DiscountCalculator implements CostManager {

    private static CostManager calculator;

    public DiscountCalculator() {}

    @Override
    public double calculateDiscount(IAccount user, double amount, String service) {
        return amount;
    }

    public static CostManager getInstance() {
        if (calculator == null) {
            calculator = new DiscountCalculator();
        }

        return calculator;
    }

    public static CostManager overallDiscount() {
        return calculator = new Overall(calculator);
    }

    public static CostManager specificDiscount(String service) {
        return calculator = new Specific(calculator, service);
    }

}
