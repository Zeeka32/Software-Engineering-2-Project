package com.fci.fawrysystem.controllers;

import com.fci.fawrysystem.controllers.payment.PayWithCreditCard;
import com.fci.fawrysystem.controllers.payment.PayWithWallet;
import com.fci.fawrysystem.controllers.payment.PaymentController;
import com.fci.fawrysystem.models.IAccount;
import com.fci.fawrysystem.models.ServiceProviders.Factory.ConcreteMobileFactory;
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
public class MobileServiceController {

    private ServiceProvider provider;
    private final PaymentController paymentController;
    private final ServiceFactory myFactory;
    private final MySystem system;

    MobileServiceController() {
        paymentController = new PaymentController();
        myFactory = new ConcreteMobileFactory();
        system = MySystem.getInstance();
    }

    @GetMapping(value = "/mobile/vodafone/form")
    public Map<String, String> vodafoneMobileForm() {
        provider = myFactory.create("Vodafone");

        return provider.serviceForm();
    }

    @PostMapping(value = "/mobile/vodafone/calculatePay")
    public Map<String, String> vodafoneMobilePaymentCalc(@RequestBody Map<String, String> payload) {

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
                    double price = paymentController.calculatePayment(account, amount, "Vodafone Mobile");

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

    @PostMapping(value = "/mobile/vodafone/pay")
    public Map<String, String> vodafoneMobilePayment(@RequestBody Map<String, String> payload) {

        double amount = Double.parseDouble(payload.get("amountToPay"));
        String email = payload.get("email");
        Vector<IAccount> loggedInUsers = system.getLoggedInUsers();
        Map<String, String> response = new HashMap<>();

        for (IAccount account : loggedInUsers) {
            if (Objects.equals(account.getEmail(), email)) {
                if(paymentController.pay(account, system, amount, "Vodafone Mobile")) {
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

    @GetMapping(value = "/mobile/etisalat/form")
    public Map<String, String> etisalatMobileForm() {
        provider = myFactory.create("Etisalat");

        return provider.serviceForm();
    }

    @PostMapping(value = "/mobile/etisalat/calculatePay")
    public Map<String, String> etisalatMobilePaymentCalc(@RequestBody Map<String, String> payload) {

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
                    double price = paymentController.calculatePayment(account, amount, "Etisalat Mobile");

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

    @PostMapping(value = "/mobile/etisalat/pay")
    public Map<String, String> etisalatMobilePayment(@RequestBody Map<String, String> payload) {

        double amount = Double.parseDouble(payload.get("amountToPay"));
        String email = payload.get("email");
        Vector<IAccount> loggedInUsers = system.getLoggedInUsers();
        Map<String, String> response = new HashMap<>();

        for (IAccount account : loggedInUsers) {
            if (Objects.equals(account.getEmail(), email)) {
                if(paymentController.pay(account, system, amount, "Etisalat Mobile")) {
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

    @GetMapping(value = "/mobile/orange/form")
    public Map<String, String> orangeMobileForm() {
        provider = myFactory.create("Orange");

        return provider.serviceForm();
    }

    @PostMapping(value = "/mobile/orange/calculatePay")
    public Map<String, String> orangeMobilePaymentCalc(@RequestBody Map<String, String> payload) {

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
                    double price = paymentController.calculatePayment(account, amount, "Orange Mobile");

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

    @PostMapping(value = "/mobile/orange/pay")
    public Map<String, String> orangeMobilePayment(@RequestBody Map<String, String> payload) {

        double amount = Double.parseDouble(payload.get("amountToPay"));
        String email = payload.get("email");
        Vector<IAccount> loggedInUsers = system.getLoggedInUsers();
        Map<String, String> response = new HashMap<>();

        for (IAccount account : loggedInUsers) {
            if (Objects.equals(account.getEmail(), email)) {
                if(paymentController.pay(account, system, amount, "Orange Mobile")) {
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

    @GetMapping(value = "/mobile/we/form")
    public Map<String, String> weMobileForm() {
        provider = myFactory.create("WE");

        return provider.serviceForm();
    }

    @PostMapping(value = "/mobile/we/calculatePay")
    public Map<String, String> weMobilePaymentCalc(@RequestBody Map<String, String> payload) {

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
                    double price = paymentController.calculatePayment(account, amount, "WE Mobile");

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

    @PostMapping(value = "/mobile/we/pay")
    public Map<String, String> weMobilePayment(@RequestBody Map<String, String> payload) {

        double amount = Double.parseDouble(payload.get("amountToPay"));
        String email = payload.get("email");
        Vector<IAccount> loggedInUsers = system.getLoggedInUsers();
        Map<String, String> response = new HashMap<>();

        for (IAccount account : loggedInUsers) {
            if (Objects.equals(account.getEmail(), email)) {
                if(paymentController.pay(account, system, amount, "WE Mobile")) {
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
