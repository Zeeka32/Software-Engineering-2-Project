package com.fci.Payment.Discounts;

import com.fci.Entities.IAccount;
import com.fci.MySystem;
import com.fci.Payment.IPaymentMethod;

public class Specific extends Discount {

    public Specific(IPaymentMethod method, String serviceName){
        this.payment = method;
        this.map.put(serviceName, true);
    }

    @Override
    public void pay(IAccount user, MySystem mySystem, double amount, String service) {
        if(map.get(service) != null) {
            amount = amount * 0.8;
            payment.pay(user, mySystem, amount, service);
        }else {
            payment.pay(user, mySystem, amount, service);
        }
    }
}
