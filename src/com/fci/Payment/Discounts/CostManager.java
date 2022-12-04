package com.fci.Payment.Discounts;


import com.fci.Entities.IAccount;
import com.fci.MySystem;

public interface CostManager {
    double calculateDiscount(IAccount user, MySystem mySystem, double amount, String service);
}
