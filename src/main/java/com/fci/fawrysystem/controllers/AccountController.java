package com.fci.fawrysystem.controllers;

import com.fci.fawrysystem.models.Admin;
import com.fci.fawrysystem.models.IAccount;
import com.fci.fawrysystem.models.Transaction;
import com.fci.fawrysystem.models.User;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Objects;
import java.util.Vector;

@RestController
public class AccountController {

    private final MySystem system;
    private final Vector<String> services;

    AccountController() {
        system = new MySystem();
        services = new Vector<>();
        services.add("Mobile Recharge Services");
        services.add("Internet Services");
        services.add("LandLine Services");
        services.add("Donation Services");
    }

    @PostMapping(value = "/signUp")
    public String signUp(@RequestBody Map<String, String> payload) {
        IAccount newAccount;

        String userName = payload.get("username");
        String email = payload.get("email");
        String password = payload.get("password");
        String type = payload.get("type");

        if (type.equalsIgnoreCase("admin")) {
            newAccount = new Admin(userName, email, password);
        } else {
            newAccount = new User(userName, email, password);
        }

        return system.signUp(newAccount);
    }

    @PostMapping(value = "/signIn")
    public String signIn(@RequestBody Map<String, String> payload) {

        String email = payload.get("email");
        String password = payload.get("password");

        return system.signIn(email, password);
    }

    @GetMapping(value = "/signOut/{email}")
    public String signOut(@PathVariable String email) {
        return system.signOut(email);
    }

    @PostMapping(value = "/addToCard")
    public String addToCreditCard(@RequestBody Map<String, String> payload) {
        String email = payload.get("email");
        double amount = Double.parseDouble(payload.get("amount"));

        Vector<IAccount> loggedInUsers = system.getLoggedInUsers();

        for (IAccount account : loggedInUsers) {
            if (Objects.equals(account.getEmail(), email)) {
                account.getCard().receive(amount);
                return amount + " has been successfully added to your Credit Card";
            }
        }

        return "couldn't add funds to this account cause: account needs to be logged in";

    }

    @PostMapping(value = "/addToWallet")
    public String addToWallet(@RequestBody Map<String, String> payload) {
        String email = payload.get("email");
        double amount = Double.parseDouble(payload.get("amount"));

        Vector<IAccount> loggedInUsers = system.getLoggedInUsers();

        for (IAccount account : loggedInUsers) {
            if (Objects.equals(account.getEmail(), email)) {
                if (account.getCard().pay(amount)) {
                    account.getWallet().receive(amount);
                    Transaction transaction = new Transaction(account, "Add to Wallet Transaction", "Bank System", amount);
                    account.getAccountHistory().addTransaction(transaction);
                    system.getSystemHistory().addTransaction(transaction);
                    return amount + "has been transferred from your creditCard to your wallet";
                }
            }
        }

        return "couldn't add funds to this account's wallet. likely, due to this account not having the requested amount or it has yet to log in to the system";

    }

    @GetMapping(value = "/Balance")
    public String showCurrentBalance(@RequestBody Map<String, String> payload) {

        String email = payload.get("email");

        Vector<IAccount> loggedInUsers = system.getLoggedInUsers();
        for (IAccount account : loggedInUsers) {
            if (Objects.equals(account.getEmail(), email)) {
                return account.getUserName() + " currently has " + account.getCard().toString() + " " + account.getWallet().toString();
            }
        }

        return "email doesn't exist";

    }

}
