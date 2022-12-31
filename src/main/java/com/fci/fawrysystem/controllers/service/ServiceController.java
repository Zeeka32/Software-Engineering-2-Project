package com.fci.fawrysystem.controllers.service;

import com.fci.fawrysystem.controllers.payment.CashOnDelivery;
import com.fci.fawrysystem.controllers.payment.PayWithCreditCard;
import com.fci.fawrysystem.controllers.payment.PayWithWallet;
import com.fci.fawrysystem.controllers.payment.PaymentController;
import com.fci.fawrysystem.models.MySystem;
import com.fci.fawrysystem.models.ServiceProviders.ConcreteServiceProviders.ServiceProvider;
import com.fci.fawrysystem.models.ServiceProviders.Factory.*;
import com.fci.fawrysystem.models.account.IAccount;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Vector;

@RestController
public class ServiceController {
    private ServiceProvider serviceProvider;
    private final PaymentController paymentController;
    private ServiceFactory myFactory;
    private final MySystem system;

    ServiceController() {
        paymentController = new PaymentController();
        system = MySystem.getInstance();
    }

    @GetMapping(value = "/{service}/form")
    public Map<String, String> getProviderForm(@RequestParam(value = "provider", required = false) String provider, @PathVariable("service") String service) {

        myFactory = createFactory(service);
        serviceProvider = myFactory.create(provider.toLowerCase());

        if(serviceProvider == null) {
            Map<String, String> response = new HashMap<>();
            response.put("error", "invalid provider");
            return response;
        }

        return serviceProvider.serviceForm();
    }

    @PostMapping(value = "/{service}/calculatePay")
    public Map<String, String> servicePaymentCalc(@PathVariable(value = "service") String service, @RequestBody Map<String, String> payload) {

        Map<String, String> response = new HashMap<>();
        double amount = Double.parseDouble(payload.get("amount"));
        String provider = payload.get("provider");
        String email = payload.get("email");

        myFactory = createFactory(service);
        serviceProvider = myFactory.create(provider.toLowerCase());

        paymentController.updateManger();

        Vector<IAccount> loggedInUsers = system.getLoggedInUsers();

        for (IAccount account : loggedInUsers) {
            if (Objects.equals(account.getEmail(), email)) {
                double price = paymentController.calculatePayment(account, amount, service.toLowerCase());

                response.put("amountToPay", String.valueOf(price));

                return response;
            }
        }

        response.put("error", "Account not logged in");
        return response;

    }

    @PostMapping(value = "/{service}/pay")
    public Map<String, String> servicePayment(@PathVariable(value = "service") String service, @RequestBody Map<String, String> payload) {

        String email = payload.get("email");
        String provider = payload.get("provider");
        String paymentType = payload.get("paymentType");
        double amount = Double.parseDouble(payload.get("amount"));

        myFactory = createFactory(service);
        serviceProvider = myFactory.create(provider.toLowerCase());

        Vector<IAccount> loggedInUsers = system.getLoggedInUsers();
        Map<String, String> response = new HashMap<>();

        if (Objects.equals(paymentType, "card")) {
            paymentController.setPaymentMethod(new PayWithCreditCard());
        } else if(Objects.equals(paymentType, "wallet")){
            paymentController.setPaymentMethod(new PayWithWallet());
        }else if(service.equalsIgnoreCase("landline")){
            paymentController.setPaymentMethod(new CashOnDelivery());
        }else {
            response.put("status", "transaction failed");
            response.put("error", "invalid payment method");
            return response;
        }

        if(!this.serviceProvider.FormHandler(payload)) {
            response.put("status", "transaction failed");
            response.put("error", "invalid amount or phone number");
            return response;
        }

        for (IAccount account : loggedInUsers) {
            if (Objects.equals(account.getEmail(), email)) {
                if(paymentController.pay(account, system, amount, provider)) {
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

    private static ServiceFactory createFactory(String service) {
        if(service.equalsIgnoreCase("mobile")) {
            return new ConcreteMobileFactory();
        }else if(service.equalsIgnoreCase("internet")) {
            return new ConcreteInternetFactory();
        }else if(service.equalsIgnoreCase("landline")) {
            return new ConcreteLandlineFactory();
        }else {
            return new ConcreteDonationsFactory();
        }
    }
}
