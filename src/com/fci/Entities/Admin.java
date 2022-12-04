package com.fci.Entities;

public class Admin extends IAccount {

    public Admin(String userName, String email, String password) {
        this.userName = userName;
        this.email = email;
        this.password = password;
        wallet = new Wallet();
        card = new CreditCard();
    }

    @Override
    public void add(IAccount acc) {
        // TODO Auto-generated method stub

    }

    @Override
    public void remove(IAccount acc) {
        // TODO Auto-generated method stub

    }

    @Override
    public void notifyAccount(IAccount acc) {
        // TODO Auto-generated method stub

    }

    @Override
    public void updateAccount(IAccount acc) {
        // TODO Auto-generated method stub

    }
}
