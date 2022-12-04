package com.fci.Payment.Discounts;


import com.fci.Entities.IAccount;

public interface CostManager {
    double calculateDiscount(IAccount user, double amount, String service);
}
