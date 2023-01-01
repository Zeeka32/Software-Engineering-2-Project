package com.fci.fawrysystem.models.ServiceProviders.Factory;

import com.fci.fawrysystem.models.ServiceProviders.ConcreteServiceProviders.*;

import java.util.Objects;

public class ConcreteMobileFactory implements ServiceFactory {
    @Override
    public ServiceProvider create(String type) {
        if(Objects.equals(type, "we")) {
            return new WEMobile();
        }else if(Objects.equals(type, "orange")) {
            return new OrangeMobile();
        }else if(Objects.equals(type, "vodafone")) {
            return new VodafoneMobile();
        }else if(Objects.equals(type, "etisalat")) {
            return new EtisalatMobile();
        }
        return null;
    }
}
