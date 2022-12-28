package com.fci.fawrysystem.models.account;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.stereotype.Component;

@Component
public abstract class IAccount {
    @JsonProperty protected String userName;
    @JsonProperty protected String email;
    @JsonProperty protected String password;
    protected Wallet wallet;
    protected CreditCard card;
    protected History accountHistory;
    protected boolean gotBonus = false;

    public IAccount() {
        accountHistory = new History();
    }

    public String getEmail() {
        return email;
    }

    public CreditCard getCard() {
        return card;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public History getAccountHistory() {
        return accountHistory;
    }

    public boolean getGotBonus() {
        return gotBonus;
    }

    public void setGotBonus(boolean gotBonus) {
        this.gotBonus = gotBonus;
    }
}