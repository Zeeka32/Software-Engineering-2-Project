package com.fci.fawrysystem.models.ServiceProviders.Factory;

import com.fci.fawrysystem.models.ServiceProviders.ConcreteServiceProviders.EtisalatInternet;
import com.fci.fawrysystem.models.ServiceProviders.ConcreteServiceProviders.OrangeInternet;
import com.fci.fawrysystem.models.ServiceProviders.ConcreteServiceProviders.VodafoneInternet;
import com.fci.fawrysystem.models.ServiceProviders.ConcreteServiceProviders.WEInternet;
import com.fci.fawrysystem.models.ServiceProviders.ConcreteServiceProviders.ServiceProvider;

public class ConcreteInternetFactory implements ServiceFactory {

    public ServiceProvider create(String type) {
        return switch (type) {
            case "we" -> new WEInternet();
            case "orange" -> new OrangeInternet();
            case "vodafone" -> new VodafoneInternet();
            case "etisalat" -> new EtisalatInternet();
            default -> null;
        };
    }
}
