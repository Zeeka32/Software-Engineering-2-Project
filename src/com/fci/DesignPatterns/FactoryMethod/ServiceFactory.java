package com.fci.DesignPatterns.FactoryMethod;

import com.fci.DesignPatterns.FactoryMethod.Services.Service;

public interface ServiceFactory {
    Service create(String type);
}
