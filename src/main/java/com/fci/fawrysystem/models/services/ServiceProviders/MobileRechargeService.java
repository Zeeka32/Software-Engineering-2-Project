package com.fci.fawrysystem.models.services.ServiceProviders;

import com.fci.fawrysystem.models.IAccount;

import java.util.Map;

public abstract class MobileRechargeService implements Service {
    public abstract void serviceForm(IAccount user);
    public abstract boolean FormHandler(Map<String, String> formData);
}
