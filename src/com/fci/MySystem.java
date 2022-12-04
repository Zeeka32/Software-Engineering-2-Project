package com.fci;

import com.fci.Entities.History;
import com.fci.Entities.IAccount;

import java.util.LinkedList;
import java.util.Objects;

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


    public boolean signIn(String email, String password) {

        for (IAccount myUser : users) {
            if (Objects.equals(myUser.getEmail(), email) && Objects.equals(myUser.getPassword(), password)) {
                activeUser = myUser;
                return true;
            }
        }
        return false;
    }

    public boolean signUp(IAccount user) {

        for (IAccount myUser : users) {
            if (Objects.equals(user.getEmail(), myUser.getEmail()) && Objects.equals(user.getPassword(), myUser.getPassword())) {
                if (user.getUserName().equals(myUser.getUserName())) {
                    return false;
                }
            }
        }

        users.add(user);
        return true;
    }

    public void signOut() {
        this.activeUser = null;
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
