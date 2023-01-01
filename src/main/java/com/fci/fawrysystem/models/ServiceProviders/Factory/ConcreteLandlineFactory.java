package com.fci.fawrysystem.models.ServiceProviders.Factory;

import com.fci.fawrysystem.models.ServiceProviders.ConcreteServiceProviders.*;

import java.util.Objects;

public class ConcreteLandlineFactory implements ServiceFactory {
    @Override
    public ServiceProvider create(String type) {
        if(Objects.equals(type, "quarterreceipt")) {
            return new QuarterReceipt();
        }else if(Objects.equals(type, "monthlyreceipt")) {
            return new MonthlyReceipt();
        }

        return null;
    }
}
