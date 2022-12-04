package com.fci.Services.Factory;

import com.fci.Services.ServiceProviders.Service;

public interface ServiceFactory {
    Service create(String type);
}
