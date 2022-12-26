package com.fci.fawrysystem.controllers;

import com.fci.fawrysystem.controllers.discount.CostManager;
import com.fci.fawrysystem.controllers.discount.DiscountCalculator;
import com.fci.fawrysystem.controllers.payment.CashOnDelivery;
import com.fci.fawrysystem.controllers.payment.IPaymentMethod;
import com.fci.fawrysystem.controllers.payment.PayWithCreditCard;
import com.fci.fawrysystem.controllers.payment.PayWithWallet;
import com.fci.fawrysystem.models.IAccount;
import com.fci.fawrysystem.models.services.Factory.ConcreteLandlineFactory;
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
public class LandlineServiceController {
    private Service provider;
    private final ServiceFactory myFactory;
    private final CostManager manager;
    private final MySystem system;

    LandlineServiceController() {
        myFactory = new ConcreteLandlineFactory();
        manager = DiscountCalculator.getInstance();
        system = MySystem.getInstance();
    }

    @GetMapping(value = "/landline/quarter/form")
    public Map<String, String> quarterReceiptForm() {
        provider = myFactory.create("QuarterReceipt");

        return provider.serviceForm();
    }

    @PostMapping(value = "/landline/quarter/pay")
    public String quarterReceiptPayment(@RequestBody Map<String, String> payload) {

        provider = myFactory.create("QuarterReceipt");

        if(provider.FormHandler(payload)) {
            double amount = Double.parseDouble(payload.get("amount"));
            String email = payload.get("email");
            String paymentType = payload.get("paymentType");
            Vector<IAccount> loggedInUsers = system.getLoggedInUsers();

            for (IAccount account : loggedInUsers) {
                if (Objects.equals(account.getEmail(), email)) {
                    calculatePayment(account, system, manager, paymentType, amount, "Landline Quarter Receipt");

                    return "Payment Complete";
                }
            }

            return "email not logged in";

        }else {
            return "invalid amount or entered phone number is not correct";
        }

    }

    @GetMapping(value = "/landline/monthly/form")
    public Map<String, String> monthlyReceiptForm() {
        provider = myFactory.create("MonthlyReceipt");

        return provider.serviceForm();
    }

    @PostMapping(value = "/landline/monthly/pay")
    public String monthlyReceiptPayment(@RequestBody Map<String, String> payload) {

        provider = myFactory.create("MonthlyReceipt");

        if(provider.FormHandler(payload)) {
            double amount = Double.parseDouble(payload.get("amount"));
            String email = payload.get("email");
            String paymentType = payload.get("paymentType");
            Vector<IAccount> loggedInUsers = system.getLoggedInUsers();

            for (IAccount account : loggedInUsers) {
                if (Objects.equals(account.getEmail(), email)) {
                    calculatePayment(account, system, manager, paymentType, amount, "Landline Monthly Receipt");

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
        }else if(Objects.equals(paymentType, "wallet")){
            paymentMethod = new PayWithWallet();
        }else {
            paymentMethod = new CashOnDelivery();
        }

        paymentMethod.pay(account, system, price, service);
    }
}
