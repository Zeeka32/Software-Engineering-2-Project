package com.fci.Services.Factory;

import com.fci.Services.ServiceProviders.ConcreteServiceProviders.*;
import com.fci.Services.ServiceProviders.Service;

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
