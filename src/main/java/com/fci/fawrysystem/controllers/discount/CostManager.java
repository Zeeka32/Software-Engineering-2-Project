package com.fci.fawrysystem.controllers.discount;


import com.fci.fawrysystem.models.IAccount;
import org.springframework.stereotype.Component;

@Component
public interface CostManager {
    double calculateDiscount(IAccount user, double amount, String service);
}
