package com.fci.Payment;

import com.fci.Entities.IAccount;
import com.fci.Entities.Transaction;
import com.fci.MySystem;

public class CashOnDelivery implements IPaymentMethod {
    @Override
    public void pay(IAccount user, MySystem mySystem, double amount, String service) {
        Transaction transaction = new Transaction(user, "Cash On Delivery", service, amount);
        user.getAccountHistory().addTransaction(transaction);
    }
}
