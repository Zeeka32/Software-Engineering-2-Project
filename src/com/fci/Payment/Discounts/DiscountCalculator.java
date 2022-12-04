package com.fci.Payment.Discounts;

import com.fci.Entities.IAccount;
import com.fci.MySystem;

public class DiscountCalculator implements CostManager {
    @Override
    public double calculateDiscount(IAccount user, MySystem mySystem, double amount, String service) {
        return amount;
    }
}
