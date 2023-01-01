package com.fci.fawrysystem.models.ServiceProviders.Factory;


import com.fci.fawrysystem.models.ServiceProviders.ConcreteServiceProviders.CancerHospital;
import com.fci.fawrysystem.models.ServiceProviders.ConcreteServiceProviders.NGO;
import com.fci.fawrysystem.models.ServiceProviders.ConcreteServiceProviders.School;
import com.fci.fawrysystem.models.ServiceProviders.ConcreteServiceProviders.ServiceProvider;

import java.util.Objects;

public class ConcreteDonationsFactory implements ServiceFactory {
    public ServiceProvider create(String type) {
        if(Objects.equals(type, "ngo")) {
            return new NGO();
        }else if(Objects.equals(type, "school")) {
            return new School();
        }else if(Objects.equals(type, "cancerhospital")) {
            return new CancerHospital();
        }
        return null;
    }
}
