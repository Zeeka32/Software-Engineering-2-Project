package com.fci.Personel;

public class Wallet {
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
        return "Wallet " + "balance = " + balance;
    }
}
