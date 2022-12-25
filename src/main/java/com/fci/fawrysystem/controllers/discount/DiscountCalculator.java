package com.fci.fawrysystem.controllers.discount;

import com.fci.fawrysystem.models.IAccount;

public class DiscountCalculator implements CostManager {

    private static CostManager calculator;

    private DiscountCalculator() {}

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
