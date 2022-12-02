package com.fci.Services.Decorator;

import com.fci.Services.Service;

public abstract class Discount extends Service {
    Service service;
    public abstract void pay();
}
