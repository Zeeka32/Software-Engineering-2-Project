package com.fci.fawrysystem.models.ServiceProviders.Factory;

import com.fci.fawrysystem.models.ServiceProviders.ConcreteServiceProviders.*;

import java.util.Objects;

public class ConcreteInternetFactory implements ServiceFactory {

    public ServiceProvider create(String type) {
        if(Objects.equals(type, "we")) {
            return new WEInternet();
        }else if(Objects.equals(type, "orange")) {
            return new OrangeInternet();
        }else if(Objects.equals(type, "vodafone")) {
            new VodafoneInternet();
        }else if(Objects.equals(type, "etisalat")) {
            return new EtisalatInternet();
        }
        return null;
    }
}
