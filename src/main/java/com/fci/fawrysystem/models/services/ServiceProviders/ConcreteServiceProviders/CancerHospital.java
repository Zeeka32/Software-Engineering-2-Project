package com.fci.fawrysystem.models.services.ServiceProviders.ConcreteServiceProviders;

import com.fci.fawrysystem.models.services.ServiceProviders.Donations;

import java.util.HashMap;
import java.util.Map;

public class CancerHospital extends Donations {

    @Override
    public Map<String, String> serviceForm() {
        Map<String, String> form = new HashMap<>();
        form.put("hospitalName", "");
        form.put("amount", "");
        form.put("paymentType", "");

        return form;
    }

    @Override
    public boolean FormHandler(Map<String, String> formData) {

        double amount = Double.parseDouble(formData.get("amount"));

        return !(amount < 0);
    }

}
