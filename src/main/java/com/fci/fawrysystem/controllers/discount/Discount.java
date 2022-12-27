package com.fci.fawrysystem.controllers.discount;

import com.fci.fawrysystem.models.IAccount;

import java.util.HashMap;


public abstract class Discount implements CostManager {
    protected CostManager manager;
    protected HashMap<String, Boolean> map = new HashMap<>();

    public abstract double calculateDiscount(IAccount user, double amount, String service);
}
