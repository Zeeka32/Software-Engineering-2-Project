package com.fci.Personel;

public class CreditCard {

    String cardNumber;
    String ccv;
    double balance = 0.0;

    public void addBalance(double amount) {
        balance += amount;
    }

    private boolean checkBalance(double amount) {
        return !(balance < amount);
    }

    public boolean payBalance(double amount) {
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
