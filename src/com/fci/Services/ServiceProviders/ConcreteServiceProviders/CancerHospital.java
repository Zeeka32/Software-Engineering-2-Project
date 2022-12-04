package com.fci.Services.ServiceProviders.ConcreteServiceProviders;

import com.fci.Entities.IAccount;
import com.fci.Services.ServiceProviders.Donations;

import java.util.Scanner;

public class CancerHospital extends Donations {
    private double amount;

    public double serviceForm(IAccount user) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Which Hospital do you want to donate to: ");
        String hospitalName = scanner.next();
        System.out.print("How much do you want to donate: ");
        amount = scanner.nextDouble();

        return FormHandler(user);
    }

    @Override
    public double FormHandler(IAccount user) {
        if (amount < 0) {
            System.out.println("Cannot donate negative values, Please try again");
            return serviceForm(user);
        }
        return amount;
    }

}
