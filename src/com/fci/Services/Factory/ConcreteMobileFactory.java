package com.fci.Services.Factory;

import com.fci.Services.ServiceProviders.ConcreteServiceProviders.*;
import com.fci.Services.ServiceProviders.Service;

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
