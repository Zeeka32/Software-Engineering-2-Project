package com.fci.Entities;

public class CreditCard implements IPaymentCard{

    String ccNumber;
    String ccv;
    String expiryDate;
    double balance = 0.0;

    public void recieve(double amount) {
        balance += amount;
    }

    private boolean checkBalance(double amount) {
        return !(balance < amount);
    }

    public boolean pay(double amount) {
        if(checkBalance(amount)){
            balance -= amount;
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "CreditCard " + "balance = " + balance;
    }
}
