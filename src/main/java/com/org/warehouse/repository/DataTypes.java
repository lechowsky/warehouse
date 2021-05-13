package com.org.warehouse.repository;

import com.org.warehouse.business.dto.InventoryDTO;
import com.org.warehouse.controller.dto.ProductDTO;
import lombok.Getter;

public enum DataTypes {
    ARTICLES("inventory.json", InventoryDTO.class),
    PRODUCTS("products.json", ProductDTO.class);

    @Getter
    private String fileName;
    @Getter
    private Class<?> clazzToMap;

    DataTypes(String fileName, Class<?> clazzToMap) {
        this.fileName = fileName;
        this.clazzToMap = clazzToMap;
    }
}
