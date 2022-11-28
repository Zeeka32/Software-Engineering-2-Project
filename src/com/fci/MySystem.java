package com.fci;

import com.fci.Entities.User;

import java.util.LinkedList;
import java.util.Objects;

public class MySystem {
    LinkedList<User> users;
    LinkedList<User> loggedInUsers;

    public MySystem() {
        users = new LinkedList<>();
    }

    private User checkValidity(String userName, String email, String password) {

        for (User myUser : users) {
            if (Objects.equals(email, myUser.getEmail()) && Objects.equals(password, myUser.getPassword())) {
                if(userName == null || userName.equals(myUser.getUserName())){
                    return myUser;
                }
            }
        }

        return null;
    }

    public boolean signIn(String email, String password) {

        User user = checkValidity(null, email, password);
        if (user != null) {
            loggedInUsers.add(user);
            return true;
        }

        return false;
    }

    public boolean signUp(String userName, String email, String password) {

        User user = checkValidity(userName, email, password);
        if (user == null) {
            user = new User(userName, email, password);
            users.add(user);
            return true;
        }

        return false;
    }

}
