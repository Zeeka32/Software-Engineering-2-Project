package com.fci.fawrysystem.controllers.discountController;

import com.fci.fawrysystem.models.IAccount;

public class DiscountCalculator implements CostManager {
    @Override
    public double calculateDiscount(IAccount user, double amount, String service) {
        return amount;
    }
}
