package com.fci.fawrysystem.models.ServiceProviders.Factory;

import com.fci.fawrysystem.models.ServiceProviders.ConcreteServiceProviders.MonthlyReceipt;
import com.fci.fawrysystem.models.ServiceProviders.ConcreteServiceProviders.QuarterReceipt;
import com.fci.fawrysystem.models.ServiceProviders.ConcreteServiceProviders.ServiceProvider;

public class ConcreteLandlineFactory implements ServiceFactory {
    @Override
    public ServiceProvider create(String type) {
        return switch (type) {
            case "QuarterReceipt" -> new QuarterReceipt();
            case "MonthlyReceipt" -> new MonthlyReceipt();
            default -> null;
        };
    }
}
