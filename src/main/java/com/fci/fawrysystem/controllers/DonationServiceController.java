package com.fci.fawrysystem.controllers;

import com.fci.fawrysystem.controllers.discount.CostManager;
import com.fci.fawrysystem.controllers.discount.DiscountCalculator;
import com.fci.fawrysystem.controllers.payment.IPaymentMethod;
import com.fci.fawrysystem.controllers.payment.PayWithCreditCard;
import com.fci.fawrysystem.controllers.payment.PayWithWallet;
import com.fci.fawrysystem.models.IAccount;
import com.fci.fawrysystem.models.services.Factory.ConcreteDonationsFactory;
import com.fci.fawrysystem.models.services.Factory.ServiceFactory;
import com.fci.fawrysystem.models.services.ServiceProviders.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;
import java.util.Objects;
import java.util.Vector;

public class DonationServiceController {
    private Service provider;
    private final ServiceFactory myFactory;
    private final CostManager manager;
    private final MySystem system;

    DonationServiceController() {
        myFactory = new ConcreteDonationsFactory();
        manager = DiscountCalculator.getInstance();
        system = MySystem.getInstance();
    }

    @PostMapping(value = "/donation/school/pay")
    public String schoolDonation(@RequestBody Map<String, String> payload) {

        provider = myFactory.create("School");

        if(provider.FormHandler(payload)) {
            double amount = Double.parseDouble(payload.get("amount"));
            String email = payload.get("email");
            String paymentType = payload.get("paymentType");
            Vector<IAccount> loggedInUsers = system.getLoggedInUsers();

            for (IAccount account : loggedInUsers) {
                if (Objects.equals(account.getEmail(), email)) {
                    calculatePayment(account, system, manager, paymentType, amount, "School Donation");

                    return "Payment Complete";
                }
            }

            return "email not logged in";

        }else {
            return "invalid amount or entered phone number is not correct";
        }

    }

    @PostMapping(value = "/donation/ngo/pay")
    public String ngoDonation(@RequestBody Map<String, String> payload) {

        provider = myFactory.create("NGO");

        if(provider.FormHandler(payload)) {
            double amount = Double.parseDouble(payload.get("amount"));
            String email = payload.get("email");
            String paymentType = payload.get("paymentType");
            Vector<IAccount> loggedInUsers = system.getLoggedInUsers();

            for (IAccount account : loggedInUsers) {
                if (Objects.equals(account.getEmail(), email)) {
                    calculatePayment(account, system, manager, paymentType, amount, "Non-Profit Organization donation");

                    return "Payment Complete";
                }
            }

            return "email not logged in";

        }else {
            return "invalid amount or entered phone number is not correct";
        }

    }

    @PostMapping(value = "/donation/hospital/pay")
    public String hospitalDonation(@RequestBody Map<String, String> payload) {

        provider = myFactory.create("CancerHospital");

        if(provider.FormHandler(payload)) {
            double amount = Double.parseDouble(payload.get("amount"));
            String email = payload.get("email");
            String paymentType = payload.get("paymentType");
            Vector<IAccount> loggedInUsers = system.getLoggedInUsers();

            for (IAccount account : loggedInUsers) {
                if (Objects.equals(account.getEmail(), email)) {
                    calculatePayment(account, system, manager, paymentType, amount, "Cancer Hospital Donation");

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
