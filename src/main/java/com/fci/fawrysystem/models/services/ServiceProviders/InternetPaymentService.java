package com.fci.fawrysystem.models.services.ServiceProviders;

import com.fci.fawrysystem.models.IAccount;

public abstract class InternetPaymentService implements Service {
    public abstract double serviceForm(IAccount user);
    public abstract double FormHandler(IAccount user);
}
