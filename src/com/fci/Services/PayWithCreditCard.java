package com.fci.Services;

import com.fci.Personel.User;
import com.fci.Services.Interfaces.IPayment;

public class PayWithCreditCard implements IPayment {

    @Override
    public boolean doService(User user, double amount) {
        return user.getCard().payBalance(amount);
    }
}
