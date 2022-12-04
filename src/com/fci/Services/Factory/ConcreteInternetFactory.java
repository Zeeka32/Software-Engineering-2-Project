package com.fci.Services.Factory;

import com.fci.Services.ServiceProviders.ConcreteServiceProviders.*;
import com.fci.Services.ServiceProviders.Service;

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
