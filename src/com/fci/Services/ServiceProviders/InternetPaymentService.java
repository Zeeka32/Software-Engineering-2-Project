package com.fci.Services.ServiceProviders;

import com.fci.Entities.IAccount;

public abstract class InternetPaymentService implements Service {
    public abstract double serviceForm(IAccount user);
    public abstract double FormHandler(IAccount user);
}
