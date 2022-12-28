package com.fci.fawrysystem.controllers.service;

import com.fci.fawrysystem.models.MySystem;
import com.fci.fawrysystem.controllers.payment.*;
import com.fci.fawrysystem.models.account.IAccount;
import com.fci.fawrysystem.models.ServiceProviders.Factory.ConcreteLandlineFactory;
import com.fci.fawrysystem.models.ServiceProviders.Factory.ServiceFactory;
import com.fci.fawrysystem.models.ServiceProviders.ConcreteServiceProviders.ServiceProvider;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Vector;

@RestController
public class LandlineServiceController {
    private ServiceProvider provider;
    private final PaymentController paymentController;
    private final ServiceFactory myFactory;
    private final MySystem system;

    LandlineServiceController() {
        paymentController = new PaymentController();
        myFactory = new ConcreteLandlineFactory();
        system = MySystem.getInstance();
    }

    @GetMapping(value = "/landline/quarter/form")
    public Map<String, String> quarterReceiptForm() {
        provider = myFactory.create("QuarterReceipt");

        return provider.serviceForm();
    }

    @PostMapping(value = "/landline/quarter/calculatePay")
    public Map<String, String> quarterReceiptPaymentCalc(@RequestBody Map<String, String> payload) {

        provider = myFactory.create("QuarterReceipt");
        Map<String, String> response = new HashMap<>();
        paymentController.updateManger();

        if (provider.FormHandler(payload)) {
            double amount = Double.parseDouble(payload.get("amount"));
            String email = payload.get("email");
            String paymentType = payload.get("paymentType");
            Vector<IAccount> loggedInUsers = system.getLoggedInUsers();

            for (IAccount account : loggedInUsers) {
                if (Objects.equals(account.getEmail(), email)) {
                    double price = paymentController.calculatePayment(account, amount, "Quarter Receipt");

                    if (Objects.equals(paymentType, "card")) {
                        paymentController.setPaymentMethod(new PayWithCreditCard());
                    } else if(Objects.equals(paymentType, "wallet")){
                        paymentController.setPaymentMethod(new PayWithWallet());
                    }else {
                        paymentController.setPaymentMethod(new CashOnDelivery());
                    }

                    response.put("amountToPay", String.valueOf(price));

                    return response;
                }
            }

            response.put("error", "email not logged in");

        } else {
            response.put("error", "invalid amount or entered phone number is not correct");
        }
        return response;

    }

    @PostMapping(value = "/landline/quarter/pay")
    public Map<String, String> quarterReceiptPayment(@RequestBody Map<String, String> payload) {

        double amount = Double.parseDouble(payload.get("amountToPay"));
        String email = payload.get("email");
        Vector<IAccount> loggedInUsers = system.getLoggedInUsers();
        Map<String, String> response = new HashMap<>();

        for (IAccount account : loggedInUsers) {
            if (Objects.equals(account.getEmail(), email)) {
                if(paymentController.pay(account, system, amount, "Quarter Receipt")) {
                    response.put("user", account.getUserName());
                    response.put("amount", String.valueOf(amount));
                    response.put("status", "transaction complete");
                    return response;
                }else {
                    response.put("user", account.getUserName());
                    response.put("amount", String.valueOf(amount));
                    response.put("status", "transaction failed");
                    response.put("error", "not enough funds");
                    return response;
                }
            }
        }

        response.put("error", "user not logged in");
        return response;
    }

    @GetMapping(value = "/landline/monthly/form")
    public Map<String, String> monthlyReceiptForm() {
        provider = myFactory.create("MonthlyReceipt");

        return provider.serviceForm();
    }

    @PostMapping(value = "/landline/monthly/calculatePay")
    public Map<String, String> monthlyReceiptPaymentCalc(@RequestBody Map<String, String> payload) {

        provider = myFactory.create("MonthlyReceipt");
        Map<String, String> response = new HashMap<>();
        paymentController.updateManger();

        if (provider.FormHandler(payload)) {
            double amount = Double.parseDouble(payload.get("amount"));
            String email = payload.get("email");
            String paymentType = payload.get("paymentType");
            Vector<IAccount> loggedInUsers = system.getLoggedInUsers();

            for (IAccount account : loggedInUsers) {
                if (Objects.equals(account.getEmail(), email)) {
                    double price = paymentController.calculatePayment(account, amount, "Monthly Receipt");

                    if (Objects.equals(paymentType, "card")) {
                        paymentController.setPaymentMethod(new PayWithCreditCard());
                    } else if(Objects.equals(paymentType, "wallet")){
                        paymentController.setPaymentMethod(new PayWithWallet());
                    }else {
                        paymentController.setPaymentMethod(new CashOnDelivery());
                    }

                    response.put("amountToPay", String.valueOf(price));

                    return response;
                }
            }

            response.put("error", "email not logged in");

        } else {
            response.put("error", "invalid amount or entered phone number is not correct");
        }
        return response;

    }

    @PostMapping(value = "/landline/monthly/pay")
    public Map<String, String> monthlyReceiptPayment(@RequestBody Map<String, String> payload) {

        double amount = Double.parseDouble(payload.get("amountToPay"));
        String email = payload.get("email");
        Vector<IAccount> loggedInUsers = system.getLoggedInUsers();
        Map<String, String> response = new HashMap<>();

        for (IAccount account : loggedInUsers) {
            if (Objects.equals(account.getEmail(), email)) {
                if(paymentController.pay(account, system, amount, "Monthly Receipt")) {
                    response.put("user", account.getUserName());
                    response.put("amount", String.valueOf(amount));
                    response.put("status", "transaction complete");
                    return response;
                }else {
                    response.put("user", account.getUserName());
                    response.put("amount", String.valueOf(amount));
                    response.put("status", "transaction failed");
                    response.put("error", "not enough funds");
                    return response;
                }
            }
        }

        response.put("error", "user not logged in");
        return response;
    }
}
