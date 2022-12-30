package com.fci.fawrysystem.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fci.fawrysystem.models.MySystem;
import com.fci.fawrysystem.models.account.Admin;
import com.fci.fawrysystem.models.account.IAccount;
import com.fci.fawrysystem.models.account.Transaction;

@RestController
public class RefundController {
    MySystem system;

    public RefundController() {
        system = MySystem.getInstance();
    }

    @PostMapping("/refund/request")
    public Map<String, String> askForRefund(@RequestBody Map<String, String> payload) {

        int id = Integer.parseInt(payload.get("ID"));
        String username = payload.get("username");

        IAccount acc = system.getAccount(username);
        Transaction T = acc.getAccountHistory().getTransactionHistory().get(id);
        Map<String, String> response = new HashMap<>();

        if (T == null || T.getType().equals("Cash On Delivery")) {
            response.put("Response", "Transaction Not Found");
            return response;
        }

        Transaction refund = new Transaction(acc, "Refund Transaction", T.getService(), T.getAmount());
        system.getRefundRequests().addTransaction(refund);
        response.put("Response", "Refund Request is pending");
        return response;
    }

    @PostMapping("/refund/review")
    public Map<String, String> reviewRefund(@RequestBody Map<String, String> payload) {

        int id = Integer.parseInt(payload.get("ID"));
        String username = payload.get("username");
        String action = payload.get("Action");

        IAccount acc = system.getAccount(username);
        Map<String, String> response = new HashMap<>();

        Transaction T = system.getRefundRequests().getTransactionHistory().get(id);
        if (!(acc instanceof Admin) || T == null || !(action.equals("accept") || !(action.equals("reject")))) {
            response.put("Response", "Invalid Request");
            return response;
        }
        if (action.equals("accept")) {
            system.getSystemHistory().addTransaction(T);
            response.put("Response", "Refund Approved");
            T.getUser().getWallet().receive(T.getAmount());
            T.getUser().getAccountHistory().addTransaction(T);
        } else {
            response.put("Response", "Refund Rejected");
        }

        system.getRefundRequests().getTransactionHistory().remove(id);
        return response;
    }
}
