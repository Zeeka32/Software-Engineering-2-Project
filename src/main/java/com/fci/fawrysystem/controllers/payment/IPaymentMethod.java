package com.fci.fawrysystem.controllers.payment;

import com.fci.fawrysystem.controllers.MySystem;
import com.fci.fawrysystem.models.IAccount;

public interface IPaymentMethod {
    boolean pay(IAccount user, MySystem mySystem, double amount, String service);
}
