package com.fci.fawrysystem.controllers;

import com.fci.fawrysystem.controllers.discount.CostManager;
import com.fci.fawrysystem.controllers.discount.DiscountCalculator;
import com.fci.fawrysystem.controllers.payment.IPaymentMethod;
import com.fci.fawrysystem.controllers.payment.PayWithCreditCard;
import com.fci.fawrysystem.controllers.payment.PayWithWallet;
import com.fci.fawrysystem.models.IAccount;
import com.fci.fawrysystem.models.services.Factory.ConcreteMobileFactory;
import com.fci.fawrysystem.models.services.Factory.ServiceFactory;
import com.fci.fawrysystem.models.services.ServiceProviders.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Objects;
import java.util.Vector;

@RestController
public class MobileServiceController {

    private Service provider;
    private final ServiceFactory myFactory;
    private final CostManager manager;
    private final MySystem system;

    MobileServiceController() {
        myFactory = new ConcreteMobileFactory();
        manager = DiscountCalculator.getInstance();
        system = MySystem.getInstance();
    }

    @GetMapping(value = "/mobile/vodafone/form")
    public Map<String, String> vodafoneMobileForm() {
        provider = myFactory.create("Vodafone");

        return provider.serviceForm();
    }

    @PostMapping(value = "/mobile/vodafone/pay")
    public String vodafoneMobilePayment(@RequestBody Map<String, String> payload) {

        provider = myFactory.create("Vodafone");

        if(provider.FormHandler(payload)) {
            double amount = Double.parseDouble(payload.get("amount"));
            String email = payload.get("email");
            String paymentType = payload.get("paymentType");
            Vector<IAccount> loggedInUsers = system.getLoggedInUsers();

            for (IAccount account : loggedInUsers) {
                if (Objects.equals(account.getEmail(), email)) {
                    calculatePayment(account, system, manager, paymentType, amount, "Vodafone Mobile");

                    return "Payment Complete";
                }
            }

            return "email not logged in";

        }else {
            return "invalid amount or entered phone number is not correct";
        }

    }

    @GetMapping(value = "/mobile/etisalat/form")
    public Map<String, String> etisalatMobileForm() {
        provider = myFactory.create("Etisalat");

        return provider.serviceForm();
    }

    @PostMapping(value = "/mobile/etisalat/pay")
    public String etisalatMobilePayment(@RequestBody Map<String, String> payload) {

        provider = myFactory.create("Etisalat");

        if(provider.FormHandler(payload)) {
            double amount = Double.parseDouble(payload.get("amount"));
            String email = payload.get("email");
            String paymentType = payload.get("paymentType");
            Vector<IAccount> loggedInUsers = system.getLoggedInUsers();

            for (IAccount account : loggedInUsers) {
                if (Objects.equals(account.getEmail(), email)) {
                    calculatePayment(account, system, manager, paymentType, amount, "Etisalat Mobile");

                    return "Payment Complete";
                }
            }

            return "email not logged in";

        }else {
            return "invalid amount or entered phone number is not correct";
        }

    }

    @GetMapping(value = "/mobile/orange/form")
    public Map<String, String> orangeMobileForm() {
        provider = myFactory.create("Orange");

        return provider.serviceForm();
    }

    @PostMapping(value = "/mobile/orange/pay")
    public String orangeMobilePayment(@RequestBody Map<String, String> payload) {

        provider = myFactory.create("Orange");

        if(provider.FormHandler(payload)) {
            double amount = Double.parseDouble(payload.get("amount"));
            String email = payload.get("email");
            String paymentType = payload.get("paymentType");
            Vector<IAccount> loggedInUsers = system.getLoggedInUsers();

            for (IAccount account : loggedInUsers) {
                if (Objects.equals(account.getEmail(), email)) {
                    calculatePayment(account, system, manager, paymentType, amount, "Orange Mobile");

                    return "Payment Complete";
                }
            }

            return "email not logged in";

        }else {
            return "invalid amount or entered phone number is not correct";
        }

    }

    @GetMapping(value = "/mobile/we/form")
    public Map<String, String> weMobileForm() {
        provider = myFactory.create("WE");

        return provider.serviceForm();
    }

    @PostMapping(value = "/mobile/we/pay")
    public String weMobilePayment(@RequestBody Map<String, String> payload) {

        provider = myFactory.create("WE");

        if(provider.FormHandler(payload)) {
            double amount = Double.parseDouble(payload.get("amount"));
            String email = payload.get("email");
            String paymentType = payload.get("paymentType");
            Vector<IAccount> loggedInUsers = system.getLoggedInUsers();

            for (IAccount account : loggedInUsers) {
                if (Objects.equals(account.getEmail(), email)) {
                    calculatePayment(account, system, manager, paymentType, amount, "WE Mobile");

                    return "Payment Complete";
                }
            }

            return "email not logged in";

        }else {
            return "invalid amount or entered phone number is not correct";
        }

    }

    private static void calculatePayment(IAccount account, MySystem system, CostManager costManager, String paymentType, double price, String service) {

        IPaymentMethod paymentMethod;
        price = costManager.calculateDiscount(account, price, service);

        if(Objects.equals(paymentType, "card")) {
            paymentMethod = new PayWithCreditCard();
        }else {
            paymentMethod = new PayWithWallet();
        }

        paymentMethod.pay(account, system, price, service);
    }
}
