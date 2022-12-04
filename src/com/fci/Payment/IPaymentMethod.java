package com.fci.Payment;

import com.fci.Entities.IAccount;
import com.fci.MySystem;

public interface IPaymentMethod {
    void pay(IAccount user, MySystem mySystem, double amount, String service);
}
