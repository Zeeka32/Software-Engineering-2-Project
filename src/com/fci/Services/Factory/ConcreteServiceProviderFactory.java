package com.fci.Services.Factory;

import com.fci.Services.ServiceProviders.ConcreteServiceProviders.VodafoneMobile;
import com.fci.Services.ServiceProviders.ConcreteServiceProviders.*;
import com.fci.Services.ServiceProvider;

public class ConcreteServiceProviderFactory implements ServiceProviderFactory {
    @Override
    public ServiceProvider create(String type) {
        return switch (type) {
            case "VodafoneMobile" -> new VodafoneMobile();
            case "EtisalatMobile" -> new EtisalatMobile();
            case "OrangeMobile" -> new OrangeMobile();
            case "WEMobile" -> new WEMobile();
            case "WEInternet" -> new WEInternet();
            case "OrangeInternet" -> new OrangeInternet();
            case "VodafoneInternet" -> new VodafoneInternet();
            case "EtisalatInternet" -> new EtisalatInternet();
            case "NGO" -> new NGO();
            case "School" -> new School();
            case "CancerHospital" -> new CancerHospital();
            case "QuarterReceipt" -> new QuarterReceipt();
            case "MonthlyReceipt" -> new MonthlyReceipt();
            default -> null;
        };
    }
}
