package com.fci.Services.ConcreteServices;

import com.fci.Services.Service;

import java.util.Vector;

public abstract class Landline extends Service {
    Vector<Service> provider = new Vector<>();

    public abstract void pay();
}
