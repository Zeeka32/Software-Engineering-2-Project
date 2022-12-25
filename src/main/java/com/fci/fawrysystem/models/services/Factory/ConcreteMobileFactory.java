package com.fci.fawrysystem.models.services.Factory;

import com.fci.fawrysystem.models.services.ServiceProviders.ConcreteServiceProviders.EtisalatMobile;
import com.fci.fawrysystem.models.services.ServiceProviders.ConcreteServiceProviders.OrangeMobile;
import com.fci.fawrysystem.models.services.ServiceProviders.ConcreteServiceProviders.VodafoneMobile;
import com.fci.fawrysystem.models.services.ServiceProviders.ConcreteServiceProviders.WEMobile;
import com.fci.fawrysystem.models.services.ServiceProviders.Service;

public class ConcreteMobileFactory implements ServiceFactory {
    @Override
    public Service create(String type) {
        return switch (type) {
            case "Vodafone" -> new VodafoneMobile();
            case "Etisalat" -> new EtisalatMobile();
            case "Orange" -> new OrangeMobile();
            case "WE" -> new WEMobile();
            default -> null;
        };
    }
}
