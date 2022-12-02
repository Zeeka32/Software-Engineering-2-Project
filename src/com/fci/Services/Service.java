package com.fci.Services;

public abstract class Service {
    IPaymentMethod payment;

    public Service() {
        payment = new PayWithCreditCard();
    }

    public void setPayment(IPaymentMethod payment) {
        this.payment = payment;
    }
}
