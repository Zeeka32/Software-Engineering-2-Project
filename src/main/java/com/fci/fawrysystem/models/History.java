package com.fci.fawrysystem.models;

import java.util.HashMap;
import java.util.Map;

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

    public String listTransactions() {
        StringBuilder list = new StringBuilder();

        for (Map.Entry<Integer, Transaction> entry : transactionHistory.entrySet()) {
            list.append(entry.getKey().toString()).append("  ").append(entry.getValue().getType()).append("  ").append(entry.getValue().getAmount()).append("  ").append(entry.getValue().getUser().getUserName()).append("  ").append(entry.getValue().getService()).append("\n");
        }
        return list.toString();
    }
}
