package com.fci.Payment.Discounts;

import com.fci.Entities.IAccount;

public class DiscountCalculator implements CostManager {
    @Override
    public double calculateDiscount(IAccount user, double amount, String service) {
        return amount;
    }
}
