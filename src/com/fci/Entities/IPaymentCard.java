package com.fci.Entities;

public interface IPaymentCard {
    boolean pay(double charge);
    void receive(double charge);
}
