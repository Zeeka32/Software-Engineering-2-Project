package com.fci.fawrysystem.models.services.ServiceProviders.ConcreteServiceProviders;


import com.fci.fawrysystem.models.services.ServiceProviders.InternetPaymentService;
import com.fci.fawrysystem.models.IAccount;

import java.util.Scanner;

public class VodafoneInternet extends InternetPaymentService {

    private String mobileNumber;
    private double amount;

    public double serviceForm(IAccount user) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Which number do you want to charge internet to: ");
        mobileNumber = scanner.next();
        System.out.print("How much do you want to pay: ");
        amount = scanner.nextDouble();

        return FormHandler(user);
    }

    @Override
    public double FormHandler(IAccount user) {
        if (amount < 0) {
            System.out.println("Cannot pay negative values, Please try again");
            serviceForm(user);
        }else if(!mobileNumber.startsWith("010")) {
            System.out.println("Invalid Vodafone number");
            return serviceForm(user);
        }
        return amount;
    }
}
