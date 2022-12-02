package com.fci.Entities;
import java.util.*;
public class User extends IAccount{
    public String userName;
    public String email;
    public String password;
    public Wallet wallet;
    public CreditCard card;
    public Vector<IAccount> admins = new Vector<IAccount>();
    public Vector<Transaction> myTransactions = new Vector<Transaction>();

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

    public boolean addToWallet(double amount) {
        if(card.pay(amount)){
            wallet.recieve(amount);;
            return true;
        }
        return false;
    }

    @Override
    public void add(IAccount acc) {
        admins.add(acc);
        
    }

    @Override
    public void remove(IAccount acc) {
        admins.remove(acc);
        
    }

    @Override
    public void notifyAccount(IAccount acc) {
        for(IAccount i : admins)
        {
            i.updateAccount(acc);
        }
        
    }

    @Override
    public void updateAccount(IAccount acc) {
        admins.remove(acc);
        System.out.println(name+"Current Balance is: "+ String.valueOf(wallet.balance));
        
    }




}
