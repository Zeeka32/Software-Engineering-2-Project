package com.fci.fawrysystem.models.services.ServiceProviders;

import java.util.Map;

public interface Service {
    Map<String, String> serviceForm();
    boolean FormHandler(Map<String, String> formData);
}
