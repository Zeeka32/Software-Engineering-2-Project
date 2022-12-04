package com.fci.Services.ServiceProviders.ConcreteServiceProviders;

import com.fci.Entities.IAccount;
import com.fci.Services.ServiceProviders.Landline;

import java.util.Scanner;

public class QuarterReceipt extends Landline {

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
            System.out.println("Cannot Pay negative values, Please try again");
            return serviceForm(user);
        }

        return amount;
    }
}
