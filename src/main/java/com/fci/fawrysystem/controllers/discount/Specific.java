package com.fci.fawrysystem.controllers.discount;

import com.fci.fawrysystem.models.IAccount;

public class Specific extends Discount {

    public Specific(CostManager manager, String serviceName){
        this.manager = manager;
        this.map.put(serviceName, true);
    }

    @Override
    public double calculateDiscount(IAccount user, double amount, String service) {
        if(map.get(service) != null) {
            amount = amount * 0.8;
        }
        return manager.calculateDiscount(user, amount, service);
    }
}
