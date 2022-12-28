package com.fci.fawrysystem.controllers.service;

import com.fci.fawrysystem.models.MySystem;
import com.fci.fawrysystem.controllers.payment.PayWithCreditCard;
import com.fci.fawrysystem.controllers.payment.PayWithWallet;
import com.fci.fawrysystem.controllers.payment.PaymentController;
import com.fci.fawrysystem.models.account.IAccount;
import com.fci.fawrysystem.models.ServiceProviders.Factory.ConcreteInternetFactory;
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
public class InternetServiceController {
    private final PaymentController paymentController;
    private final ServiceFactory myFactory;
    private ServiceProvider provider;
    private final MySystem system;

    InternetServiceController() {
        paymentController = new PaymentController();
        myFactory = new ConcreteInternetFactory();
        system = MySystem.getInstance();
    }

    @GetMapping(value = "/internet/vodafone/form")
    public Map<String, String> vodafoneInternetForm() {
        provider = myFactory.create("Vodafone");

        return provider.serviceForm();
    }

    @PostMapping(value = "/internet/vodafone/calculatePay")
    public Map<String, String> vodafoneInternetPaymentCalc(@RequestBody Map<String, String> payload) {

        provider = myFactory.create("Vodafone");
        Map<String, String> response = new HashMap<>();
        paymentController.updateManger();

        if (provider.FormHandler(payload)) {
            double amount = Double.parseDouble(payload.get("amount"));
            String email = payload.get("email");
            String paymentType = payload.get("paymentType");
            Vector<IAccount> loggedInUsers = system.getLoggedInUsers();

            for (IAccount account : loggedInUsers) {
                if (Objects.equals(account.getEmail(), email)) {
                    double price = paymentController.calculatePayment(account, amount, "Vodafone Internet");

                    if (Objects.equals(paymentType, "card")) {
                        paymentController.setPaymentMethod(new PayWithCreditCard());
                    } else {
                        paymentController.setPaymentMethod(new PayWithWallet());
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

    @PostMapping(value = "/internet/vodafone/pay")
    public Map<String, String> vodafoneInternetPayment(@RequestBody Map<String, String> payload) {

        double amount = Double.parseDouble(payload.get("amountToPay"));
        String email = payload.get("email");
        Vector<IAccount> loggedInUsers = system.getLoggedInUsers();
        Map<String, String> response = new HashMap<>();

        for (IAccount account : loggedInUsers) {
            if (Objects.equals(account.getEmail(), email)) {
                if(paymentController.pay(account, system, amount, "Vodafone Internet")) {
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

    @GetMapping(value = "/internet/etisalat/form")
    public Map<String, String> etisalatInternetForm() {
        provider = myFactory.create("Etisalat");

        return provider.serviceForm();
    }

    @PostMapping(value = "/internet/etisalat/calculatePay")
    public Map<String, String> etisalatInternetPaymentCalc(@RequestBody Map<String, String> payload) {

        provider = myFactory.create("Etisalat");
        Map<String, String> response = new HashMap<>();
        paymentController.updateManger();

        if (provider.FormHandler(payload)) {
            double amount = Double.parseDouble(payload.get("amount"));
            String email = payload.get("email");
            String paymentType = payload.get("paymentType");
            Vector<IAccount> loggedInUsers = system.getLoggedInUsers();

            for (IAccount account : loggedInUsers) {
                if (Objects.equals(account.getEmail(), email)) {
                    double price = paymentController.calculatePayment(account, amount, "Etisalat Internet");

                    if (Objects.equals(paymentType, "card")) {
                        paymentController.setPaymentMethod(new PayWithCreditCard());
                    } else {
                        paymentController.setPaymentMethod(new PayWithWallet());
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

    @PostMapping(value = "/internet/etisalat/pay")
    public Map<String, String> etisalatInternetPayment(@RequestBody Map<String, String> payload) {

        double amount = Double.parseDouble(payload.get("amountToPay"));
        String email = payload.get("email");
        Vector<IAccount> loggedInUsers = system.getLoggedInUsers();
        Map<String, String> response = new HashMap<>();

        for (IAccount account : loggedInUsers) {
            if (Objects.equals(account.getEmail(), email)) {
                if(paymentController.pay(account, system, amount, "Etisalat Internet")) {
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

    @GetMapping(value = "/internet/orange/form")
    public Map<String, String> orangeInternetForm() {
        provider = myFactory.create("Orange");

        return provider.serviceForm();
    }

    @PostMapping(value = "/internet/orange/calculatePay")
    public Map<String, String> orangeInternetPaymentCalc(@RequestBody Map<String, String> payload) {

        provider = myFactory.create("Orange");
        Map<String, String> response = new HashMap<>();
        paymentController.updateManger();

        if (provider.FormHandler(payload)) {
            double amount = Double.parseDouble(payload.get("amount"));
            String email = payload.get("email");
            String paymentType = payload.get("paymentType");
            Vector<IAccount> loggedInUsers = system.getLoggedInUsers();

            for (IAccount account : loggedInUsers) {
                if (Objects.equals(account.getEmail(), email)) {
                    double price = paymentController.calculatePayment(account, amount, "Orange Internet");

                    if (Objects.equals(paymentType, "card")) {
                        paymentController.setPaymentMethod(new PayWithCreditCard());
                    } else {
                        paymentController.setPaymentMethod(new PayWithWallet());
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

    @PostMapping(value = "/internet/orange/pay")
    public Map<String, String> orangeInternetPayment(@RequestBody Map<String, String> payload) {

        double amount = Double.parseDouble(payload.get("amountToPay"));
        String email = payload.get("email");
        Vector<IAccount> loggedInUsers = system.getLoggedInUsers();
        Map<String, String> response = new HashMap<>();

        for (IAccount account : loggedInUsers) {
            if (Objects.equals(account.getEmail(), email)) {
                if(paymentController.pay(account, system, amount, "Orange Internet")) {
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

    @GetMapping(value = "/internet/we/form")
    public Map<String, String> weInternetForm() {
        provider = myFactory.create("WE");

        return provider.serviceForm();
    }

    @PostMapping(value = "/internet/we/calculatePay")
    public Map<String, String> weInternetPaymentCalc(@RequestBody Map<String, String> payload) {

        provider = myFactory.create("WE");
        Map<String, String> response = new HashMap<>();
        paymentController.updateManger();

        if (provider.FormHandler(payload)) {
            double amount = Double.parseDouble(payload.get("amount"));
            String email = payload.get("email");
            String paymentType = payload.get("paymentType");
            Vector<IAccount> loggedInUsers = system.getLoggedInUsers();

            for (IAccount account : loggedInUsers) {
                if (Objects.equals(account.getEmail(), email)) {
                    double price = paymentController.calculatePayment(account, amount, "WE Internet");

                    if (Objects.equals(paymentType, "card")) {
                        paymentController.setPaymentMethod(new PayWithCreditCard());
                    } else {
                        paymentController.setPaymentMethod(new PayWithWallet());
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

    @PostMapping(value = "/internet/we/pay")
    public Map<String, String> weInternetPayment(@RequestBody Map<String, String> payload) {

        double amount = Double.parseDouble(payload.get("amountToPay"));
        String email = payload.get("email");
        Vector<IAccount> loggedInUsers = system.getLoggedInUsers();
        Map<String, String> response = new HashMap<>();

        for (IAccount account : loggedInUsers) {
            if (Objects.equals(account.getEmail(), email)) {
                if(paymentController.pay(account, system, amount, "WE Internet")) {
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
