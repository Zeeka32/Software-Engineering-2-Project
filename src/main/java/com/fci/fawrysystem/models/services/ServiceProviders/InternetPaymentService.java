package com.fci.fawrysystem.models.services.ServiceProviders;

import java.util.Map;

public abstract class InternetPaymentService implements Service {
    public abstract Map<String, String> serviceForm();
    public abstract boolean FormHandler(Map<String, String> formData);
}
