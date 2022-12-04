package com.fci.Payment;

import com.fci.Entities.IAccount;
import com.fci.Entities.Transaction;
import com.fci.MySystem;

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
