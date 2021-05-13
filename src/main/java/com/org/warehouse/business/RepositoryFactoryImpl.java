package com.org.warehouse.business;

import com.org.warehouse.repository.DataTypes;
import com.org.warehouse.repository.InventoryRepository;
import com.org.warehouse.repository.ProductsRepository;
import com.org.warehouse.repository.Repository;
import javax.inject.Singleton;

@Singleton
public class RepositoryFactoryImpl implements RepositoryFactory {

    private ProductsRepository productsRepository;
    private InventoryRepository inventoryRepository;

    public RepositoryFactoryImpl(ProductsRepository productsRepository,
        InventoryRepository inventoryRepository) {
        this.productsRepository = productsRepository;
        this.inventoryRepository = inventoryRepository;
    }

    @Override
    public Repository get(DataTypes dataTypes) {
        if(DataTypes.PRODUCTS.equals(dataTypes)){
            return productsRepository;
        } else {
            return inventoryRepository;
        }
    }
}
