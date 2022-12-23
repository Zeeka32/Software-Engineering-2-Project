package com.fci.fawrysystem.models.services.Factory;

import com.fci.fawrysystem.models.services.ServiceProviders.ConcreteServiceProviders.EtisalatInternet;
import com.fci.fawrysystem.models.services.ServiceProviders.ConcreteServiceProviders.OrangeInternet;
import com.fci.fawrysystem.models.services.ServiceProviders.ConcreteServiceProviders.VodafoneInternet;
import com.fci.fawrysystem.models.services.ServiceProviders.ConcreteServiceProviders.WEInternet;
import com.fci.fawrysystem.models.services.ServiceProviders.Service;

public class ConcreteInternetFactory implements ServiceFactory {

    public Service create(String type) {
        return switch (type) {
            case "WEInternet" -> new WEInternet();
            case "OrangeInternet" -> new OrangeInternet();
            case "VodafoneInternet" -> new VodafoneInternet();
            case "EtisalatInternet" -> new EtisalatInternet();
            default -> null;
        };
    }
}
