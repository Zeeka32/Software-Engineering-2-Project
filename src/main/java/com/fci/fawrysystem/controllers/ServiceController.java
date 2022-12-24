package com.fci.fawrysystem.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Vector;

@RestController
public class ServiceController {
    private final MySystem system;
    private final Vector<String> services;
    private final Vector<String> mobileProviders;
    private final Vector<String> internetProviders;
    private final Vector<String> landlineProviders;
    private final Vector<String> donationProviders;

    public ServiceController() {
        this.system = new MySystem();
        this.services = new Vector<>();
        this.mobileProviders = new Vector<>();
        this.internetProviders = new Vector<>();
        this.landlineProviders = new Vector<>();
        this.donationProviders = new Vector<>();

        services.add("Mobile Recharge Services");
        services.add("Internet Services");
        services.add("LandLine Services");
        services.add("Donation Services");

        mobileProviders.add("Vodafone");
        mobileProviders.add("Etisalat");
        mobileProviders.add("Orange");
        mobileProviders.add("WE");

        internetProviders.add("Vodafone");
        internetProviders.add("Etisalat");
        internetProviders.add("Orange");
        internetProviders.add("WE");

        landlineProviders.add("Quarter receipt");
        landlineProviders.add("Monthly receipt");

        donationProviders.add("Schools");
        donationProviders.add("Hospitals");
        donationProviders.add("NGO");

    }

    @GetMapping(value = "/mobile/providers")
    public String getMobileProviders() {
        StringBuilder response = new StringBuilder();

        for(String provider : mobileProviders) {
            response.append(provider).append("\n");
        }

        return response.toString();
    }

    @GetMapping(value = "/internet/providers")
    public String getInternetProviders() {
        StringBuilder response = new StringBuilder();

        for(String provider : internetProviders) {
            response.append(provider).append("\n");
        }

        return response.toString();
    }

    @GetMapping(value = "/landline/providers")
    public String getLandlineProviders() {
        StringBuilder response = new StringBuilder();

        for(String provider : landlineProviders) {
            response.append(provider).append("\n");
        }

        return response.toString();
    }

    @GetMapping(value = "/donation/providers")
    public String getDonationProviders() {
        StringBuilder response = new StringBuilder();

        for(String provider : donationProviders) {
            response.append(provider).append("\n");
        }

        return response.toString();
    }

    @GetMapping(value = "/search/{query}")
    public String search(@PathVariable String query) {

        StringBuilder result = new StringBuilder();

        for (String str : services) {
            if (str.toLowerCase().contains(query.toLowerCase())) {
                result.append(str).append("\n");
            }
        }
        return result.toString();
    }
}
