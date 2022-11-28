package com.fci.DesignPatterns.FactoryMethod;

import com.fci.DesignPatterns.FactoryMethod.Services.*;

import java.util.Objects;

public class ConcreteServiceFactory implements ServiceFactory {

    @Override
    public Service create(String type) {

        if(Objects.equals(type, "Mobile")){
            return new MobileRecharge();
        }

        if(Objects.equals(type, "Internet")){
            return new InternetPayment();
        }

        if(Objects.equals(type, "Landline")){
            return new LandlineService();
        }

        if(Objects.equals(type, "Donation")){
            return new Donations();
        }

        return null;
    }
}
