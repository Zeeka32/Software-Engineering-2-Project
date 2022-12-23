package com.fci.fawrysystem.models.services.Factory;

import com.fci.fawrysystem.models.services.ServiceProviders.Service;

public interface ServiceFactory {
    Service create(String type);
}
