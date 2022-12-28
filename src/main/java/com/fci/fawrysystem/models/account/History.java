package com.fci.fawrysystem.models.account;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Vector;

public class History {
    private final HashMap<Integer, Transaction> transactionHistory;
    private int ID = 0;

    public History() {
        transactionHistory = new HashMap<>();
    }

    public HashMap<Integer, Transaction> getTransactionHistory() {
        return transactionHistory;
    }

    public void addTransaction(Transaction transaction) {
        ID++;
        transactionHistory.put(ID, transaction);
    }

    public Vector<LinkedHashMap<String, String>>  listTransactions() {
        Vector<LinkedHashMap<String, String>> transactionList = new Vector<>();

        for (Map.Entry<Integer, Transaction> entry : transactionHistory.entrySet()) {
            LinkedHashMap<String, String> transaction = new LinkedHashMap<>();
            transaction.put("Transaction ID", entry.getKey().toString());
            transaction.put("userName", entry.getValue().getUser().getUserName());
            transaction.put("Transaction Type", entry.getValue().getType());
            transaction.put("amount", String.valueOf(entry.getValue().getAmount()));
            transaction.put("service", entry.getValue().getService());
            transactionList.add(transaction);
        }
        return transactionList;
    }
}
