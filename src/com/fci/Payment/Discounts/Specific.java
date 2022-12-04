package com.fci.Payment.Discounts;

import com.fci.Entities.IAccount;
import com.fci.MySystem;

public class Specific extends Discount {

    public Specific(CostManager manager, String serviceName){
        this.manager = manager;
        this.map.put(serviceName, true);
    }

    @Override
    public double calculateDiscount(IAccount user, MySystem mySystem, double amount, String service) {
        if(map.get(service) != null) {
            amount = amount * 0.8;
        }
        return manager.calculateDiscount(user, mySystem, amount, service);
    }
}
