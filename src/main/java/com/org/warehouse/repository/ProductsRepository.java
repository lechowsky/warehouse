package com.org.warehouse.repository;

import com.org.warehouse.repository.entity.ProductsEntity;
import java.util.List;

public interface ProductsRepository extends Repository<ProductsEntity> {

    List<ProductsEntity> findAll();

}
