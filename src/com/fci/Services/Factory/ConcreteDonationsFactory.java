package com.fci.Services.Factory;

import com.fci.Payment.IPaymentMethod;
import com.fci.Services.ServiceProviders.ConcreteServiceProviders.*;
import com.fci.Services.ServiceProviders.Service;

public class ConcreteDonationsFactory implements ServiceFactory {
    public Service create(java.lang.String type, IPaymentMethod paymentMethod) {
        return switch (type) {
            case "NGO" -> new NGO(paymentMethod);
            case "School" -> new School(paymentMethod);
            case "CancerHospital" -> new CancerHospital(paymentMethod);
            default -> null;
        };
    }
}
