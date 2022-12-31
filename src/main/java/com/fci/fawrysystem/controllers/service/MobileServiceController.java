package com.fci.fawrysystem.controllers.service;

import com.fci.fawrysystem.models.MySystem;
import com.fci.fawrysystem.controllers.payment.PayWithCreditCard;
import com.fci.fawrysystem.controllers.payment.PayWithWallet;
import com.fci.fawrysystem.controllers.payment.PaymentController;
import com.fci.fawrysystem.models.account.IAccount;
import com.fci.fawrysystem.models.ServiceProviders.Factory.ConcreteMobileFactory;
import com.fci.fawrysystem.models.ServiceProviders.Factory.ServiceFactory;
import com.fci.fawrysystem.models.ServiceProviders.ConcreteServiceProviders.ServiceProvider;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Vector;

@RestController
public class MobileServiceController {

    private ServiceProvider serviceProvider;
    private final PaymentController paymentController;
    private final ServiceFactory myFactory;
    private final MySystem system;

    MobileServiceController() {
        paymentController = new PaymentController();
        myFactory = new ConcreteMobileFactory();
        system = MySystem.getInstance();
    }

    @GetMapping(value = "/mobile/form")
    public Map<String, String> getProviderForm(@RequestParam(value = "provider") String provider) {
        serviceProvider = myFactory.create(provider);

        if(serviceProvider == null) {
            Map<String, String> response = new HashMap<>();
            response.put("error", "invalid provider");
            return response;
        }

        return serviceProvider.serviceForm();
    }

    @PostMapping(value = "/mobile/calculatePay")
    public Map<String, String> mobilePaymentCalc(@RequestBody Map<String, String> payload) {

        String provider = payload.get("provider");
        double amount = Double.parseDouble(payload.get("amount"));
        String email = payload.get("email");

        this.serviceProvider = myFactory.create(provider);
        Map<String, String> response = new HashMap<>();
        paymentController.updateManger();

        Vector<IAccount> loggedInUsers = system.getLoggedInUsers();

        for (IAccount account : loggedInUsers) {
            if (Objects.equals(account.getEmail(), email)) {
                double price = paymentController.calculatePayment(account, amount, provider + " Mobile");

                response.put("amountToPay", String.valueOf(price));

                return response;
            }
        }

        response.put("error", "Account not logged in");
        return response;

    }

    @PostMapping(value = "/mobile/pay")
    public Map<String, String> mobilePayment(@RequestBody Map<String, String> payload) {

        String name = payload.get("provider");
        String paymentType = payload.get("paymentType");
        double amount = Double.parseDouble(payload.get("amount"));
        serviceProvider = myFactory.create(name);
        String email = payload.get("email");

        Vector<IAccount> loggedInUsers = system.getLoggedInUsers();
        Map<String, String> response = new HashMap<>();

        if (Objects.equals(paymentType, "card")) {
            paymentController.setPaymentMethod(new PayWithCreditCard());
        } else {
            paymentController.setPaymentMethod(new PayWithWallet());
        }

        if(!this.serviceProvider.FormHandler(payload)) {
            response.put("error", "invalid amount or incorrect phone number");
            return response;
        }

        for (IAccount account : loggedInUsers) {
            if (Objects.equals(account.getEmail(), email)) {
                if(paymentController.pay(account, system, amount, name + " Mobile")) {
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
