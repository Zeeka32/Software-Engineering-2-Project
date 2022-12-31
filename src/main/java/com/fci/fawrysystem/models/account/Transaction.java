package com.fci.fawrysystem.models.account;

public class Transaction {
    IAccount user;
    String type;
    String string;
    double amount;
    boolean pending;
    boolean isRefunded;

    public Transaction(IAccount user, String type, String string, double amount) {
        this.user = user;
        this.type = type;
        this.string = string;
        this.amount = amount;
        this.pending = false;
        this.isRefunded = false;
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

    public void setPending(boolean pending) {
        this.pending = pending;
    }

    public boolean isPending() {
        return pending;
    }

    public void setRefunded(boolean refunded) {
        isRefunded = refunded;
    }

    public boolean isRefunded() {
        return isRefunded;
    }
}
