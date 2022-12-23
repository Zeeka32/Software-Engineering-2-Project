package com.fci.fawrysystem.models.services.Factory;

import com.fci.fawrysystem.models.services.ServiceProviders.ConcreteServiceProviders.MonthlyReceipt;
import com.fci.fawrysystem.models.services.ServiceProviders.ConcreteServiceProviders.QuarterReceipt;
import com.fci.fawrysystem.models.services.ServiceProviders.Service;

public class ConcreteLandlineFactory implements ServiceFactory {
    @Override
    public Service create(String type) {
        return switch (type) {
            case "QuarterReceipt" -> new QuarterReceipt();
            case "MonthlyReceipt" -> new MonthlyReceipt();
            default -> null;
        };
    }
}
