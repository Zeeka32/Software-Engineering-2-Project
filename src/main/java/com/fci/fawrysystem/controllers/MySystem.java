package com.fci.fawrysystem.controllers;

import com.fci.fawrysystem.models.*;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Vector;

public class MySystem {
    LinkedList<IAccount> users;
    private static MySystem mainSystem;
    private final Vector<IAccount> loggedInUsers;
    private final History systemHistory;
    private final History refundRequests;

    private MySystem() {
        users = new LinkedList<>();
        systemHistory = new History();
        refundRequests = new History();
        loggedInUsers = new Vector<>();
    }

    public String signIn(String email, String password) {

        for(IAccount user : loggedInUsers) {
            if(Objects.equals(user.getEmail(), email)) {
                return user.getUserName() + " is already signed in to the system";
            }
        }

        for (IAccount myUser : users) {
            if (Objects.equals(myUser.getEmail(), email) && Objects.equals(myUser.getPassword(), password)) {
                loggedInUsers.add(myUser);
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

    public String signOut(String email) {
        for(IAccount user : loggedInUsers) {
            if(Objects.equals(user.getEmail(), email)) {
                loggedInUsers.remove(user);
                return user.getUserName() + " has been logged out of the system successfully";
            }
        }
        return "couldn't sign out the given email";
    }

    public static MySystem getInstance() {

        if(mainSystem == null) {
            mainSystem = new MySystem();
        }

        return mainSystem;
    }

    public Vector<IAccount> getLoggedInUsers() {
        return loggedInUsers;
    }

    public History getSystemHistory() {
        return systemHistory;
    }

    public History getRefundRequests() {
        return refundRequests;
    }
}
