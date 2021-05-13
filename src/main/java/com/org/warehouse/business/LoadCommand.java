package com.org.warehouse.business;

import com.org.warehouse.repository.DataTypes;

public interface LoadCommand{

    boolean isType(DataTypes dataTypes);

    void execute(DataTypes dataType, String payload);
}
