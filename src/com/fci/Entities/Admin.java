package com.fci.Entities;
import java.util.*;

public class Admin extends IAccount{
    String userName;
    String email;
    String password;
    public Vector<Transaction> allTransactions = new Vector<Transaction>();
    public Admin(String userName, String email, String password) {
        this.userName = userName;
        this.email = email;
        this.password = password;
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
