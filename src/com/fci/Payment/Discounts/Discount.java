package com.fci.Payment.Discounts;

import com.fci.Entities.IAccount;

import java.util.HashMap;


public abstract class Discount implements CostManager {
    protected CostManager manager;
    protected HashMap<String, Boolean> map = new HashMap<>();

    public abstract double calculateDiscount(IAccount user, double amount, String service);
}
