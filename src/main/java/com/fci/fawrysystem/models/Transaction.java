package com.fci.fawrysystem.models;

public class Transaction {
    IAccount user;
    String type;
    String string;
    double amount;

    public Transaction(IAccount user, String type, String string, double amount) {
        this.user = user;
        this.type = type;
        this.string = string;
        this.amount = amount;
    }

    public String getService() {
        return string;
    }

    public IAccount getUser() {
        return user;
    }

    public String getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
