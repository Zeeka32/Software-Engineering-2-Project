package com.fci.fawrysystem.models.services.ServiceProviders.ConcreteServiceProviders;

import com.fci.fawrysystem.models.services.ServiceProviders.MobileRechargeService;
import com.fci.fawrysystem.models.IAccount;

import java.util.Map;
import java.util.Scanner;

public class OrangeMobile extends MobileRechargeService {


    public void serviceForm(IAccount user) {

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
