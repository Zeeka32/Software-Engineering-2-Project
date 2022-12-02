package com.fci.Services;

import com.fci.Services.Payment.IPaymentMethod;
import com.fci.Services.Payment.PayWithCreditCard;

public abstract class Service {
    protected IPaymentMethod payment;

    public Service() {
        payment = new PayWithCreditCard();
    }

    public void setPayment(IPaymentMethod payment) {
        this.payment = payment;
    }

    public abstract void pay();
}
