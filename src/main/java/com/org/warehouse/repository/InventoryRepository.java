package com.org.warehouse.repository;

import com.org.warehouse.repository.entity.InventoryEntity;
import java.util.List;

public interface InventoryRepository extends Repository<InventoryEntity>   {

    List<InventoryEntity> findAll();

}
