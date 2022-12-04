package com.fci.Payment.Discounts;

import com.fci.Entities.IAccount;
import com.fci.MySystem;
import com.fci.Payment.IPaymentMethod;

public class NoDiscount extends Discount {

    public NoDiscount(IPaymentMethod paymentMethod) {
        this.payment = paymentMethod;
    }

    @Override
    public void pay(IAccount user, MySystem mySystem, double amount, String service) {
        payment.pay(user, mySystem, amount, service);
    }
}
