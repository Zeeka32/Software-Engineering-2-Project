package com.fci.Payment.Discounts;

import com.fci.Entities.IAccount;
import com.fci.MySystem;
import com.fci.Payment.IPaymentMethod;

public class Overall extends Discount {

    public Overall(IPaymentMethod paymentMethod) {
        this.payment = paymentMethod;
    }

    @Override
    public void pay(IAccount user, MySystem mySystem, double amount, String service) {

        if(!user.getGotBonus()) {
            amount = amount * 0.9;
            payment.pay(user, mySystem, amount, service);
            user.setGotBonus(true);
        }else {
            payment.pay(user, mySystem, amount, service);
        }
    }
}
