package com.fci.fawrysystem.controllers.discountController;


import com.fci.fawrysystem.models.IAccount;

public interface CostManager {
    double calculateDiscount(IAccount user, double amount, String service);
}
