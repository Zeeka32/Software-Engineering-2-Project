package com.fci.Services.Factory;

import com.fci.Payment.IPaymentMethod;
import com.fci.Services.ServiceProviders.Service;

public interface ServiceFactory {
    Service create(String type, IPaymentMethod paymentMethod);
}
