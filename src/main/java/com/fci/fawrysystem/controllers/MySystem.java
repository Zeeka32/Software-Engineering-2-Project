package com.fci.fawrysystem.controllers;

import com.fci.fawrysystem.models.*;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.Objects;

@Component
public class MySystem {
    LinkedList<IAccount> users;
    private IAccount activeUser;
    private final History systemHistory;
    private final History refundRequests;

    public MySystem() {
        users = new LinkedList<>();
        systemHistory = new History();
        refundRequests = new History();
    }

    public String signIn(String email, String password) {

        if(activeUser != null) {
            return "already signed in to the system";
        }

        for (IAccount myUser : users) {
            if (Objects.equals(myUser.getEmail(), email) && Objects.equals(myUser.getPassword(), password)) {
                activeUser = myUser;
                return "successfully signed in";
            }
        }
        return "incorrect credentials or accounts doesn't exist";
    }

    public String signUp(IAccount user) {
        for (IAccount myUser : users) {
            if (Objects.equals(user.getEmail(), myUser.getEmail()) && Objects.equals(user.getPassword(), myUser.getPassword())) {
                if (user.getUserName().equals(myUser.getUserName())) {
                    return "account already exists";
                }
            }
        }

        users.add(user);
        return "account added successfully";
    }

    public String signOut() {
        this.activeUser = null;
        return "signed out successfully";
    }

    public IAccount getActiveUser() {
        return activeUser;
    }

    public History getSystemHistory() {
        return systemHistory;
    }

    public History getRefundRequests() {
        return refundRequests;
    }
}
