package com.org.warehouse.business;

import com.org.warehouse.repository.DataTypes;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class LoadCommandFactoryImpl implements LoadCommandFactory {

    @Inject
    Instance<LoadCommand> loadCommands;

    @Override
    public LoadCommand get(DataTypes dataTypes) {
        return loadCommands.stream().filter(loadCommand -> loadCommand.isType(dataTypes)).findFirst().get();
    }
}
