package com.fci.Services.ServiceProviders.ConcreteServiceProviders;

import com.fci.Entities.IAccount;
import com.fci.Payment.IPaymentMethod;
import com.fci.Payment.PayWithCreditCard;
import com.fci.Payment.PayWithWallet;
import com.fci.Services.ServiceProviders.InternetPaymentService;

import java.util.Scanner;

public class VodafoneInternet extends InternetPaymentService {

    private IPaymentMethod paymentMethod;
    private String mobileNumber;
    private double amount;

    public VodafoneInternet(IPaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public void setPaymentMethod(IPaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public double serviceForm(IAccount user) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Which number do you want to charge internet to: ");
        mobileNumber = scanner.next();
        System.out.print("How much do you want to pay: ");
        amount = scanner.nextDouble();
        System.out.println("How would you like to pay this amount ?");

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
