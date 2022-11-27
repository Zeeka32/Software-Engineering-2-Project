package com.fci.Services.Interfaces;

import com.fci.Personel.User;

public interface IPayment {
    boolean doService(User user, double amount);
}
