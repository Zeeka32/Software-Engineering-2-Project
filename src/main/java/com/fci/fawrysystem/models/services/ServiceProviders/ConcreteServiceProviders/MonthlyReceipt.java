package com.fci.fawrysystem.models.services.ServiceProviders.ConcreteServiceProviders;

import com.fci.fawrysystem.models.services.ServiceProviders.Landline;

import java.util.HashMap;
import java.util.Map;

public class MonthlyReceipt extends Landline {

    @Override
    public Map<String, String> serviceForm() {
        Map<String, String> form = new HashMap<>();
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
