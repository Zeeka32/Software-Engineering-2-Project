package com.fci.fawrysystem.controllers;
import com.fci.fawrysystem.models.account.Admin;
import com.fci.fawrysystem.models.account.IAccount;
import com.fci.fawrysystem.models.MySystem;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Vector;

@RestController
public class TransactionsController {
    private final MySystem system;

    public TransactionsController() {
        system = MySystem.getInstance();
    }

    @GetMapping(value = "/transaction")
    public Vector<LinkedHashMap<String, String>> getTransactionHistory(@RequestParam(value = "userName") String userName) {
        IAccount account = system.getAccount(userName);

        if(account != null) {
            return account.getAccountHistory().listTransactions();
        }

        return null;
    }

    @GetMapping(value = "/transaction/all")
    public Vector<LinkedHashMap<String, String>>  getAllTransactionHistory(@RequestParam(value = "userName") String userName) {
        IAccount account = system.getAccount(userName);

        if(account instanceof Admin) {
            return system.getSystemHistory().listTransactions();
        }

        return null;
    }
}
