package com.fci.fawrysystem.controllers.paymentController;


import com.fci.fawrysystem.controllers.MySystem;
import com.fci.fawrysystem.models.IAccount;
import com.fci.fawrysystem.models.Transaction;

public class CashOnDelivery implements IPaymentMethod {
    @Override
    public void pay(IAccount user, MySystem mySystem, double amount, String service) {
        Transaction transaction = new Transaction(user, "Cash On Delivery", service, amount);
        user.getAccountHistory().addTransaction(transaction);
    }
}
