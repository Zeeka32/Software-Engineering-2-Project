package com.fci.fawrysystem.models.ServiceProviders.ConcreteServiceProviders;

import java.util.HashMap;
import java.util.Map;

public class OrangeInternet implements ServiceProvider {

    @Override
    public Map<String, String> serviceForm() {
        Map<String, String> form = new HashMap<>();
        form.put("number", "");
        form.put("amount", "");
        form.put("paymentType", "");

        return form;

    }

    @Override
    public boolean FormHandler(Map<String, String> formData) {

        double amount = Double.parseDouble(formData.get("amount"));
        String mobileNumber = formData.get("number");

        if (amount < 0) {
            return false;
        }else return mobileNumber.startsWith("012");
    }

}
