package com.fci.Services.Factory;

import com.fci.Payment.IPaymentMethod;
import com.fci.Services.ServiceProviders.ConcreteServiceProviders.*;
import com.fci.Services.ServiceProviders.Service;

public class ConcreteLandlineFactory implements ServiceFactory {
    @Override
    public Service create(java.lang.String type, IPaymentMethod paymentMethod) {
        return switch (type) {
            case "QuarterReceipt" -> new QuarterReceipt(paymentMethod);
            case "MonthlyReceipt" -> new MonthlyReceipt(paymentMethod);
            default -> null;
        };
    }
}
