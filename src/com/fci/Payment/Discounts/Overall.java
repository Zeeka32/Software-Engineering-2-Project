package com.fci.Payment.Discounts;

import com.fci.Entities.IAccount;
import com.fci.MySystem;

public class Overall extends Discount {

    public Overall(CostManager manager) {
        this.manager = manager;
    }

    @Override
    public double calculateDiscount(IAccount user, MySystem mySystem, double amount, String service) {

        if(!user.getGotBonus()) {
            amount = amount * 0.9;
            user.setGotBonus(true);
        }
        return manager.calculateDiscount(user, mySystem, amount, service);
    }
}
