package com.fci.fawrysystem.models.ServiceProviders.Factory;


import com.fci.fawrysystem.models.ServiceProviders.ConcreteServiceProviders.CancerHospital;
import com.fci.fawrysystem.models.ServiceProviders.ConcreteServiceProviders.NGO;
import com.fci.fawrysystem.models.ServiceProviders.ConcreteServiceProviders.School;
import com.fci.fawrysystem.models.ServiceProviders.ConcreteServiceProviders.ServiceProvider;

public class ConcreteDonationsFactory implements ServiceFactory {
    public ServiceProvider create(String type) {
        return switch (type) {
            case "NGO" -> new NGO();
            case "School" -> new School();
            case "CancerHospital" -> new CancerHospital();
            default -> null;
        };
    }
}
