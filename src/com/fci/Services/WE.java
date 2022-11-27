package com.fci.Services;

import com.fci.Personel.User;
import com.fci.Services.Interfaces.IPayment;
import com.fci.Services.Interfaces.InternetPayment;
import com.fci.Services.Interfaces.MobileRecharge;

public class WE implements InternetPayment, MobileRecharge {
    private IPayment payment;

    public WE() {
        payment = new PayWithCreditCard();
    }

    public void setPayment(IPayment payment) {
        this.payment = payment;
    }

    @Override
    public void payInternet() {

    }

    @Override
    public boolean rechargeBalance(User user, double amount) {
        return payment.doService(user, amount);
    }
}
