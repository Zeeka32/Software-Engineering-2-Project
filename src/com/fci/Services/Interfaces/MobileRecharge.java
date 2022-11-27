package com.fci.Services.Interfaces;

import com.fci.Personel.User;

public interface MobileRecharge {
    boolean rechargeBalance(User user, double amount);
}
