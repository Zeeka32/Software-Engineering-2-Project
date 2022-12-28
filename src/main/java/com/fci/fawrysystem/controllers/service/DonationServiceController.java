package com.fci.fawrysystem.controllers.service;

import com.fci.fawrysystem.models.MySystem;
import com.fci.fawrysystem.controllers.payment.PayWithCreditCard;
import com.fci.fawrysystem.controllers.payment.PayWithWallet;
import com.fci.fawrysystem.controllers.payment.PaymentController;
import com.fci.fawrysystem.models.account.IAccount;
import com.fci.fawrysystem.models.ServiceProviders.Factory.ConcreteDonationsFactory;
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
public class DonationServiceController {

    private final PaymentController paymentController;
    private final ServiceFactory myFactory;
    private ServiceProvider provider;
    private final MySystem system;

    DonationServiceController() {
        paymentController = new PaymentController();
        myFactory = new ConcreteDonationsFactory();
        system = MySystem.getInstance();
    }

    @GetMapping(value = "/donation/school/form")
    public Map<String, String> schoolDonationForm() {
        provider = myFactory.create("School");

        return provider.serviceForm();
    }

    @PostMapping(value = "/donation/school/calculatePay")
    public Map<String, String> schoolDonationCalc(@RequestBody Map<String, String> payload) {

        provider = myFactory.create("School");
        Map<String, String> response = new HashMap<>();
        paymentController.updateManger();

        if (provider.FormHandler(payload)) {
            double amount = Double.parseDouble(payload.get("amount"));
            String email = payload.get("email");
            String paymentType = payload.get("paymentType");
            Vector<IAccount> loggedInUsers = system.getLoggedInUsers();

            for (IAccount account : loggedInUsers) {
                if (Objects.equals(account.getEmail(), email)) {
                    double price = paymentController.calculatePayment(account, amount, "School Donation");

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

    @PostMapping(value = "/donation/school/pay")
    public Map<String, String> schoolDonation(@RequestBody Map<String, String> payload) {

        double amount = Double.parseDouble(payload.get("amountToPay"));
        String email = payload.get("email");
        Vector<IAccount> loggedInUsers = system.getLoggedInUsers();
        Map<String, String> response = new HashMap<>();

        for (IAccount account : loggedInUsers) {
            if (Objects.equals(account.getEmail(), email)) {
                if(paymentController.pay(account, system, amount, "School Donation")) {
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

    @GetMapping(value = "/donation/ngo/form")
    public Map<String, String> ngoDonationForm() {
        provider = myFactory.create("NGO");

        return provider.serviceForm();
    }

    @PostMapping(value = "/donation/ngo/calculatePay")
    public Map<String, String> ngoDonationCalc(@RequestBody Map<String, String> payload) {

        provider = myFactory.create("NGO");
        Map<String, String> response = new HashMap<>();
        paymentController.updateManger();

        if (provider.FormHandler(payload)) {
            double amount = Double.parseDouble(payload.get("amount"));
            String email = payload.get("email");
            String paymentType = payload.get("paymentType");
            Vector<IAccount> loggedInUsers = system.getLoggedInUsers();

            for (IAccount account : loggedInUsers) {
                if (Objects.equals(account.getEmail(), email)) {
                    double price = paymentController.calculatePayment(account, amount, "NGO");

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

    @PostMapping(value = "/donation/ngo/pay")
    public Map<String, String> ngoDonation(@RequestBody Map<String, String> payload) {

        double amount = Double.parseDouble(payload.get("amountToPay"));
        String email = payload.get("email");
        Vector<IAccount> loggedInUsers = system.getLoggedInUsers();
        Map<String, String> response = new HashMap<>();

        for (IAccount account : loggedInUsers) {
            if (Objects.equals(account.getEmail(), email)) {
                if(paymentController.pay(account, system, amount, "Non-Profit Organization")) {
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

    @GetMapping(value = "/donation/hospital/form")
    public Map<String, String> hospitalDonationForm() {
        provider = myFactory.create("CancerHospital");

        return provider.serviceForm();
    }

    @PostMapping(value = "/donation/hospital/calculatePay")
    public Map<String, String> hospitalDonationCalc(@RequestBody Map<String, String> payload) {

        provider = myFactory.create("CancerHospital");
        Map<String, String> response = new HashMap<>();
        paymentController.updateManger();

        if (provider.FormHandler(payload)) {
            double amount = Double.parseDouble(payload.get("amount"));
            String email = payload.get("email");
            String paymentType = payload.get("paymentType");
            Vector<IAccount> loggedInUsers = system.getLoggedInUsers();

            for (IAccount account : loggedInUsers) {
                if (Objects.equals(account.getEmail(), email)) {
                    double price = paymentController.calculatePayment(account, amount, "Cancer Hospital");

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

    @PostMapping(value = "/donation/hospital/pay")
    public Map<String, String> hospitalDonation(@RequestBody Map<String, String> payload) {

        double amount = Double.parseDouble(payload.get("amountToPay"));
        String email = payload.get("email");
        Vector<IAccount> loggedInUsers = system.getLoggedInUsers();
        Map<String, String> response = new HashMap<>();

        for (IAccount account : loggedInUsers) {
            if (Objects.equals(account.getEmail(), email)) {
                if(paymentController.pay(account, system, amount, "Cancer Hospital Donation")) {
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
