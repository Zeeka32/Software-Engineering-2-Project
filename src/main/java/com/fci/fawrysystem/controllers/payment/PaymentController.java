package com.fci.fawrysystem.controllers.payment;

import com.fci.fawrysystem.models.MySystem;
import com.fci.fawrysystem.controllers.discount.CostManager;
import com.fci.fawrysystem.controllers.discount.DiscountCalculator;
import com.fci.fawrysystem.models.account.IAccount;

public class PaymentController {

    private IPaymentMethod paymentMethod;
    private CostManager manager;

    public PaymentController() {
        paymentMethod = new PayWithCreditCard();
        manager = DiscountCalculator.getInstance();
    }

    public void setPaymentMethod(IPaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public boolean pay(IAccount account, MySystem system, double price, String service) {
        return paymentMethod.pay(account, system, price, service);
    }

    public double calculatePayment(IAccount account, double price, String service) {
        price = manager.calculateDiscount(account, price, service);
        return price;
    }

    public void updateManger() {
        manager = DiscountCalculator.getInstance();
    }

}
