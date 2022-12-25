package com.fci.fawrysystem.models.services.ServiceProviders.ConcreteServiceProviders;


import com.fci.fawrysystem.models.services.ServiceProviders.Landline;
import com.fci.fawrysystem.models.IAccount;

import java.util.Map;

public class QuarterReceipt extends Landline {

    @Override
    public void serviceForm(IAccount user) {

    }

    @Override
    public boolean FormHandler(Map<String, String> formData) {

        double amount = Double.parseDouble(formData.get("amount"));

        return !(amount < 0);
    }
}
