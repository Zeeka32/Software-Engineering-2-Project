package com.fci.fawrysystem.models.account;

public interface IPaymentCard {
    boolean pay(double charge);
    void receive(double charge);
}
