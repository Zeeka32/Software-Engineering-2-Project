package com.fci.Services.ServiceProviders.ConcreteServiceProviders;

import com.fci.Entities.IAccount;
import com.fci.Payment.IPaymentMethod;
import com.fci.Payment.PayWithCreditCard;
import com.fci.Payment.PayWithWallet;
import com.fci.Services.ServiceProviders.Donations;

import java.util.Scanner;

public class School extends Donations {

    private double amount;
    private String schoolName;
    private IPaymentMethod paymentMethod;

    public School(IPaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public void setPaymentMethod(IPaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    @Override
    public double serviceForm(IAccount user) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Which School do you want to donate to: ");
        schoolName = scanner.next();
        System.out.print("How much do you want to donate: ");
        amount = scanner.nextDouble();

        int paymentOption = -1;
        while(paymentOption != 1 && paymentOption != 2) {
            System.out.println("1-Credit Card");
            System.out.println("2-Wallet");
            paymentOption = scanner.nextInt();
        }

        if(paymentOption == 1) {
            setPaymentMethod(new PayWithCreditCard());
        }else {
            setPaymentMethod(new PayWithWallet());
        }

        return FormHandler(user);
    }

    @Override
    public double FormHandler(IAccount user) {
        if(amount < 0) {
            System.out.println("Cannot donate negative values, Please try again");
            return serviceForm(user);
        }

        return amount;
    }
}
