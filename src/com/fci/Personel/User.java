package com.fci.Personel;

public class User {
    String userName;
    String email;
    String password;
    Wallet wallet;
    CreditCard card;

    public User(String userName, String email, String password) {
        this.userName = userName;
        this.email = email;
        this.password = password;
        wallet = new Wallet();
        card = new CreditCard();
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getUserName() {
        return userName;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public CreditCard getCard() {
        return card;
    }
}
