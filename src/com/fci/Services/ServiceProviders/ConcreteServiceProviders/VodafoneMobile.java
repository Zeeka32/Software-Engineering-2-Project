package com.fci.Services.ServiceProviders.ConcreteServiceProviders;

import com.fci.Entities.IAccount;
import com.fci.Services.ServiceProviders.MobileRechargeService;

import java.util.Scanner;

public class VodafoneMobile extends MobileRechargeService {

    private double amount;
    private String mobileNumber;

    public double serviceForm(IAccount user) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Which number do you want to charge balance to: ");
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
