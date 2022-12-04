package com.fci.Services.ServiceProviders.ConcreteServiceProviders;

import com.fci.Entities.IAccount;
import com.fci.Payment.CashOnDelivery;
import com.fci.Payment.IPaymentMethod;
import com.fci.Payment.PayWithCreditCard;
import com.fci.Payment.PayWithWallet;
import com.fci.Services.ServiceProviders.Landline;

import java.util.Scanner;

public class QuarterReceipt extends Landline {

    private double amount;
    private String monthName;

    private IPaymentMethod paymentMethod;

    public QuarterReceipt(IPaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public void setPaymentMethod(IPaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    @Override
    public double serviceForm(IAccount user) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Which Quarter: ");
        monthName = scanner.next();
        System.out.print("How much do you want to pay: ");
        amount = scanner.nextDouble();

        int paymentOption = -1;
        while(paymentOption != 1 && paymentOption != 2 && paymentOption != 3) {
            System.out.println("1-Credit Card");
            System.out.println("2-Wallet");
            System.out.println("3-Cash On Delivery");
            paymentOption = scanner.nextInt();
        }

        if(paymentOption == 1) {
            setPaymentMethod(new PayWithCreditCard());
        }else if(paymentOption == 2){
            setPaymentMethod(new PayWithWallet());
        }else {
            setPaymentMethod(new CashOnDelivery());
        }

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
