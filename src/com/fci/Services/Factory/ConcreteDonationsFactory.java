package com.fci.Services.Factory;

import com.fci.Services.ServiceProviders.ConcreteServiceProviders.*;
import com.fci.Services.ServiceProviders.Service;

public class ConcreteDonationsFactory implements ServiceFactory {
    public Service create(String type) {
        return switch (type) {
            case "NGO" -> new NGO();
            case "School" -> new School();
            case "CancerHospital" -> new CancerHospital();
            default -> null;
        };
    }
}
