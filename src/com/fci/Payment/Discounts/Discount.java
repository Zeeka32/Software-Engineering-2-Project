package com.fci.Payment.Discounts;

import com.fci.Entities.IAccount;
import com.fci.MySystem;
import com.fci.Payment.IPaymentMethod;

import java.util.HashMap;


public abstract class Discount implements IPaymentMethod {
    protected IPaymentMethod payment;
    protected HashMap<String, Boolean> map = new HashMap<>();

    public abstract void pay(IAccount user, MySystem mySystem, double amount, String service);
}
