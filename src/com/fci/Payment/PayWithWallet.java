package com.fci.Payment;

import com.fci.Entities.IAccount;
import com.fci.Entities.Transaction;
import com.fci.MySystem;

public class PayWithWallet implements IPaymentMethod {
    @Override
    public void pay(IAccount user, MySystem mySystem, double amount, String service) {
        if(user.getWallet().pay(amount)) {
            Transaction transaction = new Transaction(user, "Payment Transaction", service, amount);
            user.getAccountHistory().addTransaction(transaction);
            mySystem.getSystemHistory().addTransaction(transaction);
        }
    }
}
