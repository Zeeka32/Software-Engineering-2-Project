package com.fci.fawrysystem.controllers.payment;

import com.fci.fawrysystem.models.MySystem;
import com.fci.fawrysystem.models.account.IAccount;
import com.fci.fawrysystem.models.account.Transaction;

public class PayWithCreditCard implements IPaymentMethod {
    @Override
    public boolean pay(IAccount user, MySystem mySystem, double amount, String service) {
        if(user.getCard().pay(amount)) {
            Transaction transaction = new Transaction(user, "Payment Transaction with Credit Card", service, amount);
            user.getAccountHistory().addTransaction(transaction);
            mySystem.getSystemHistory().addTransaction(transaction);
            return true;
        }

        return false;
    }
}
