package com.fci.fawrysystem.models.services.ServiceProviders.ConcreteServiceProviders;

import com.fci.fawrysystem.models.services.ServiceProviders.Landline;
import com.fci.fawrysystem.models.IAccount;

import java.util.Scanner;

public class MonthlyReceipt extends Landline {

    private double amount;
    private String monthName;

    @Override
    public double serviceForm(IAccount user) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Which Quarter: ");
        monthName = scanner.next();
        System.out.print("How much do you want to pay: ");
        amount = scanner.nextDouble();

        return FormHandler(user);
    }

    @Override
    public double FormHandler(IAccount user) {
        if(amount < 0) {
            System.out.println("Cannot pay negative values, Please try again");
            return serviceForm(user);
        }

        return amount;
    }
}
