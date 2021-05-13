package com.org.warehouse.business;

import com.org.warehouse.repository.DataTypes;
import com.org.warehouse.repository.LoadData;
import javax.inject.Singleton;

@Singleton
public class LoaderImpl implements Loader {

    private LoadData loadData;
    private LoadCommandFactory factory;

    public LoaderImpl(LoadData loadData, LoadCommandFactory factory) {
        this.loadData = loadData;
        this.factory = factory;
    }

    @Override
    public void load(DataTypes dataTypes) {
        LoadDataResult loadedData = loadData.load(dataTypes);
        factory.get(dataTypes).execute(dataTypes, loadedData.getResult());
    }
}
