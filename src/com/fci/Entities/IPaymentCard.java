package com.fci.Entities;

public interface IPaymentCard {

    public boolean pay(double charge);
    public void recieve(double charge);
}
