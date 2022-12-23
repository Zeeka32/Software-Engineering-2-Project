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
            case "VodafoneMobile" -> new VodafoneMobile();
            case "EtisalatMobile" -> new EtisalatMobile();
            case "OrangeMobile" -> new OrangeMobile();
            case "WEMobile" -> new WEMobile();
            default -> null;
        };
    }
}
