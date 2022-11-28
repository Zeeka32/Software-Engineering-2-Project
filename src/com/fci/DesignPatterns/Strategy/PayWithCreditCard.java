package com.fci.DesignPatterns.Strategy;

import com.fci.Entities.User;

public class PayWithCreditCard implements IPayment {

    @Override
    public boolean doService(User user, double amount) {
        return user.getCard().payBalance(amount);
    }
}
