package com.fci.fawrysystem.controllers.paymentController;

import com.fci.fawrysystem.controllers.MySystem;
import com.fci.fawrysystem.models.IAccount;

public interface IPaymentMethod {
    void pay(IAccount user, MySystem mySystem, double amount, String service);
}
