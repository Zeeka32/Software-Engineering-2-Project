package com.fci.Entities;

import com.fci.DesignPatterns.Strategy.IPayment;
import com.fci.DesignPatterns.Strategy.PayWithCreditCard;

public class ServiceProvider {
    private final String name;
    private IPayment payment;

    public ServiceProvider(String name) {
        this.name = name;
        payment = new PayWithCreditCard();
    }

    public String getName() {
        return name;
    }

    public boolean doService(User user, double amount) {
        return payment.doService(user, amount);
    }

    public void setPayment(IPayment payment) {
        this.payment = payment;
    }
}
