package com.org.warehouse.repository;

import javax.inject.Singleton;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@Singleton
public class LoadDataProperties {


    @ConfigProperty(name = "warehouse.data.location")
    private String location;

    public String getLocation() {
        return location;
    }
}
