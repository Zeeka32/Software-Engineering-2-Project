package com.fci.fawrysystem.controllers.discountController;

import com.fci.fawrysystem.models.IAccount;

public class Overall extends Discount {

    public Overall(CostManager manager) {
        this.manager = manager;
    }

    @Override
    public double calculateDiscount(IAccount user, double amount, String service) {

        if(!user.getGotBonus()) {
            amount = amount * 0.9;
            user.setGotBonus(true);
        }
        return manager.calculateDiscount(user, amount, service);
    }
}
