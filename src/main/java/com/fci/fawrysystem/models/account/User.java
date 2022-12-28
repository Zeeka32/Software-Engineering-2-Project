package com.fci.fawrysystem.models.account;

public class User extends IAccount {
    public User(String userName, String email, String password) {
        this.userName = userName;
        this.email = email;
        this.password = password;
        wallet = new Wallet();
        card = new CreditCard();
    }
}
