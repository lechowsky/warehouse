package com.org.warehouse.repository;

import com.org.warehouse.business.LoadDataResult;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import javax.inject.Singleton;

@Singleton
public class LoadDataFromSystem implements LoadData {

    private final LoadDataProperties properties;

    public LoadDataFromSystem(LoadDataProperties properties) {
        this.properties=properties;
    }

    @Override
    public LoadDataResult load(DataTypes products) {
        String jsonData;
        try {
            jsonData = Files.readString(
                Path.of(buildLoadPath(properties.getLocation(),products.getFileName()))
            );
        } catch (IOException e) {
            throw new RuntimeException("error loading file",e);
        }
        return LoadDataResult.builder().result(jsonData).build();
    }

    private String buildLoadPath(String location, String fileName){
        return location + File.separator + fileName;
    }
}
