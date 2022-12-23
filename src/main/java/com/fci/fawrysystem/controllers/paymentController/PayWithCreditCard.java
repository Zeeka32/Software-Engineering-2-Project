package com.fci.fawrysystem.controllers.paymentController;

import com.fci.fawrysystem.controllers.MySystem;
import com.fci.fawrysystem.models.IAccount;
import com.fci.fawrysystem.models.Transaction;

public class PayWithCreditCard implements IPaymentMethod {
    @Override
    public void pay(IAccount user, MySystem mySystem, double amount, String service) {
        if(user.getCard().pay(amount)) {
            Transaction transaction = new Transaction(user, "Payment Transaction with Credit Card", service, amount);
            user.getAccountHistory().addTransaction(transaction);
            mySystem.getSystemHistory().addTransaction(transaction);
        }
    }
}