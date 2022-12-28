package com.fci.fawrysystem.controllers.payment;

import com.fci.fawrysystem.models.MySystem;
import com.fci.fawrysystem.models.account.IAccount;

public interface IPaymentMethod {
    boolean pay(IAccount user, MySystem mySystem, double amount, String service);
}
