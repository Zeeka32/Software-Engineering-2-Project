package com.fci.Services.Factory;

import com.fci.Payment.IPaymentMethod;
import com.fci.Services.ServiceProviders.ConcreteServiceProviders.*;
import com.fci.Services.ServiceProviders.Service;

public class ConcreteMobileFactory implements ServiceFactory {
    @Override
    public Service create(java.lang.String type, IPaymentMethod paymentMethod) {
        return switch (type) {
            case "VodafoneMobile" -> new VodafoneMobile(paymentMethod);
            case "EtisalatMobile" -> new EtisalatMobile(paymentMethod);
            case "OrangeMobile" -> new OrangeMobile(paymentMethod);
            case "WEMobile" -> new WEMobile(paymentMethod);
            default -> null;
        };
    }
}
