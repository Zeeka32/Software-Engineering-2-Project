package com.fci.fawrysystem.models.account;

public class CreditCard implements IPaymentCard {
    double balance = 0.0;

    public void receive(double amount) {
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
