package com.fci.Services;

import com.fci.Personel.User;
import com.fci.Services.Interfaces.IPayment;

public class PayWithWallet implements IPayment {

    @Override
    public boolean doService(User user, double amount) {
        return user.getWallet().payBalance(amount);
    }
}
