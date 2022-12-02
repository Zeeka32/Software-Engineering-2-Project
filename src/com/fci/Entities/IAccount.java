package com.fci.Entities;

public abstract class IAccount {
    String name, userName, email, password;
    Integer ID;

    public abstract void add(IAccount acc);
    public abstract void remove(IAccount acc);
    public abstract void notifyAccount(IAccount acc);
    public abstract void updateAccount(IAccount acc);

}
