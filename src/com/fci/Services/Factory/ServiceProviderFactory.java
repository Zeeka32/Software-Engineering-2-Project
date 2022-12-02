package com.fci.Services.Factory;

import com.fci.Services.ServiceProvider;

public interface ServiceProviderFactory {
    ServiceProvider create(String type);
}
