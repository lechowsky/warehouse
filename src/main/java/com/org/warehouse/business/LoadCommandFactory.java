package com.org.warehouse.business;

import com.org.warehouse.repository.DataTypes;

public interface LoadCommandFactory {

    LoadCommand get(DataTypes dataTypes);
}
