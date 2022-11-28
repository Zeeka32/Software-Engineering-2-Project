package com.fci.DesignPatterns.Strategy;

import com.fci.Entities.User;

public class PayWithWallet implements IPayment {

    @Override
    public boolean doService(User user, double amount) {
        return user.getWallet().payBalance(amount);
    }
}
