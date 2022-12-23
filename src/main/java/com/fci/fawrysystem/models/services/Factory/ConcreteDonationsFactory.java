package com.fci.fawrysystem.models.services.Factory;


import com.fci.fawrysystem.models.services.ServiceProviders.ConcreteServiceProviders.CancerHospital;
import com.fci.fawrysystem.models.services.ServiceProviders.ConcreteServiceProviders.NGO;
import com.fci.fawrysystem.models.services.ServiceProviders.ConcreteServiceProviders.School;
import com.fci.fawrysystem.models.services.ServiceProviders.Service;

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
