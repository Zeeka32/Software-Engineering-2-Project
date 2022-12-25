package com.fci.fawrysystem.models.services.ServiceProviders;

import com.fci.fawrysystem.models.IAccount;

import java.util.Map;

public interface Service {
    void serviceForm(IAccount user);
    boolean FormHandler(Map<String, String> formData);
}
