package com.fci.DesignPatterns.Strategy;

import com.fci.Entities.User;

public interface IPayment {
    boolean doService(User user, double amount);
}
