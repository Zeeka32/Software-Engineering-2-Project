package com.fci.Entities;

public class Transaction {
    static int ID;
    History history;
    public boolean pay(IPaymentCard type,User user,double amount)
    {
        if(type instanceof Wallet)
        {
            return user.wallet.pay(amount);
        }else
        {
            return user.card.pay(amount);
        }
    }
    public boolean refund(IPaymentCard type,User user,double amount)
    {
        if(type instanceof Wallet)
        {
            return user.wallet.pay(amount);
        }else
        {
            user.card.recieve(amount);
            return true;
        }
    }
}
