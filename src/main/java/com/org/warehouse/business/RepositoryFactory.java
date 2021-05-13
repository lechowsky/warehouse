package com.org.warehouse.business;

import com.org.warehouse.repository.DataTypes;
import com.org.warehouse.repository.Repository;

public interface RepositoryFactory {

        Repository get(DataTypes dataTypes);
}
