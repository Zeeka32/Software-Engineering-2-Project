package com.fci.Services.Factory;

import com.fci.Payment.IPaymentMethod;
import com.fci.Services.ServiceProviders.ConcreteServiceProviders.*;
import com.fci.Services.ServiceProviders.Service;

public class ConcreteInternetFactory implements ServiceFactory {

    public Service create(java.lang.String type, IPaymentMethod paymentMethod) {
        return switch (type) {
            case "WEInternet" -> new WEInternet(paymentMethod);
            case "OrangeInternet" -> new OrangeInternet(paymentMethod);
            case "VodafoneInternet" -> new VodafoneInternet(paymentMethod);
            case "EtisalatInternet" -> new EtisalatInternet(paymentMethod);
            default -> null;
        };
    }
}
