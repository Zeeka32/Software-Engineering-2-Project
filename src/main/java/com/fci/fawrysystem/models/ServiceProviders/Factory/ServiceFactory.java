package com.fci.fawrysystem.models.ServiceProviders.Factory;

import com.fci.fawrysystem.models.ServiceProviders.ConcreteServiceProviders.ServiceProvider;

public interface ServiceFactory {
    ServiceProvider create(String type);
}
