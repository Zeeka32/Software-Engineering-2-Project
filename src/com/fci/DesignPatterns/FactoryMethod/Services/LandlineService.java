package com.fci.DesignPatterns.FactoryMethod.Services;

import com.fci.DesignPatterns.FactoryMethod.Services.Service;
import com.fci.Entities.ServiceProvider;
import com.fci.Entities.User;

import java.util.HashMap;

public class LandlineService implements Service {

    HashMap<String, ServiceProvider> providers = new HashMap<>();

    @Override
    public boolean pay(ServiceProvider prov, User user, double amount) {
        return providers.get(prov.getName()).doService(user, amount);
    }

    public void addServiceProvider(ServiceProvider s) {
        providers.put(s.getName(), s);
    }

    public void removeProvider(ServiceProvider s) {
        providers.remove(s);
    }
}
