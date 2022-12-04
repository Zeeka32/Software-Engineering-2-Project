package com.fci.Payment.Discounts;

import com.fci.Entities.IAccount;
import com.fci.MySystem;

import java.util.HashMap;


public abstract class Discount implements CostManager {
    protected CostManager manager;
    protected HashMap<String, Boolean> map = new HashMap<>();

    public abstract double calculateDiscount(IAccount user, MySystem mySystem, double amount, String service);
}
