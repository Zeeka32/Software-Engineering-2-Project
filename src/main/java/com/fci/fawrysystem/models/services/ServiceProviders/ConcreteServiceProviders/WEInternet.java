package com.fci.fawrysystem.models.services.ServiceProviders.ConcreteServiceProviders;

import com.fci.fawrysystem.models.services.ServiceProviders.InternetPaymentService;
import com.fci.fawrysystem.models.IAccount;

import java.util.Scanner;

public class WEInternet extends InternetPaymentService {

    private double amount;
    private String mobileNumber;

    public double serviceForm(IAccount user) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Which number do you want to charge Internet to: ");
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
        }else if(!mobileNumber.startsWith("015")) {
            System.out.println("Invalid WE number");
            return serviceForm(user);
        }
        return amount;
    }
}
