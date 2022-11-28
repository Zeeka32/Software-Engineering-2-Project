package com.fci.DesignPatterns.FactoryMethod.Services;

import com.fci.Entities.ServiceProvider;
import com.fci.Entities.User;

public interface Service {
    boolean pay(ServiceProvider prov, User user, double amount);
    void addServiceProvider(ServiceProvider s);
    void removeProvider(ServiceProvider s);
}
