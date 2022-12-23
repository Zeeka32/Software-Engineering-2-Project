package com.fci.fawrysystem.models;

public interface IPaymentCard {
    boolean pay(double charge);
    void receive(double charge);
}
