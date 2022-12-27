package com.fci.fawrysystem.models.ServiceProviders.ConcreteServiceProviders;

import java.util.Map;

public interface ServiceProvider {
    Map<String, String> serviceForm();
    boolean FormHandler(Map<String, String> formData);
}
