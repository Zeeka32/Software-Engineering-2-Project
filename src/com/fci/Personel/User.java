package com.fci.Personel;

public class User {
    String userName;
    String email;
    String password;
    Wallet wallet;

    public User(String userName, String email, String password) {
        this.userName = userName;
        this.email = email;
        this.password = password;
        wallet = new Wallet();
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
}
