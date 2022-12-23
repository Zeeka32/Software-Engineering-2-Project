package com.fci.fawrysystem.controllers;

import com.fci.fawrysystem.models.Admin;
import com.fci.fawrysystem.models.IAccount;
import com.fci.fawrysystem.models.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class serviceController {

    private final MySystem system;

    serviceController() {
        system = new MySystem();
    }

    @PostMapping(value = "/signUp")
    public String signUp(@RequestBody Map<String, String> payload) {
        IAccount newAccount;

        String userName = payload.get("username");
        String email = payload.get("email");
        String password = payload.get("password");
        String type = payload.get("type");

        if(type.equalsIgnoreCase("admin")) {
            newAccount = new Admin(userName, email, password);
        }else{
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

    @GetMapping(value = "/signOut")
    public String signOut() {
        return system.signOut();
    }
}
