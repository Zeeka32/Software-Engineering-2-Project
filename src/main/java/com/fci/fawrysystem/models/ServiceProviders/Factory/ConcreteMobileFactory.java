package com.fci.fawrysystem.models.ServiceProviders.Factory;

import com.fci.fawrysystem.models.ServiceProviders.ConcreteServiceProviders.EtisalatMobile;
import com.fci.fawrysystem.models.ServiceProviders.ConcreteServiceProviders.OrangeMobile;
import com.fci.fawrysystem.models.ServiceProviders.ConcreteServiceProviders.VodafoneMobile;
import com.fci.fawrysystem.models.ServiceProviders.ConcreteServiceProviders.ServiceProvider;
import com.fci.fawrysystem.models.ServiceProviders.ConcreteServiceProviders.WEMobile;

public class ConcreteMobileFactory implements ServiceFactory {
    @Override
    public ServiceProvider create(String type) {
        return switch (type) {
            case "Vodafone" -> new VodafoneMobile();
            case "Etisalat" -> new EtisalatMobile();
            case "Orange" -> new OrangeMobile();
            case "WE" -> new WEMobile();
            default -> null;
        };
    }
}
