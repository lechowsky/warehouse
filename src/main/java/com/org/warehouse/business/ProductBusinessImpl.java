package com.org.warehouse.business;

import com.org.warehouse.repository.InventoryRepository;
import com.org.warehouse.repository.ProductsRepository;
import com.org.warehouse.repository.entity.InventoryEntity;
import com.org.warehouse.repository.entity.ProductsEntity;
import javax.inject.Singleton;
import javax.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Singleton
public class ProductBusinessImpl implements ProductBusiness {

    private ProductsRepository productsRepository;
    private InventoryRepository inventoryRepository;

    public ProductBusinessImpl(ProductsRepository productsRepository,
        InventoryRepository inventoryRepository) {
        this.productsRepository = productsRepository;
        this.inventoryRepository = inventoryRepository;
    }

    @Transactional
    @Override
    public void sell(Integer id) {
        ProductsEntity productsEntity = productsRepository.find(id);
        productsEntity.getContainArticles().stream()
            .map(containArticleEntity -> containArticleEntity.getInventory())
            .map(inventoryEntity -> inventoryEntity.getArtId())
            .map(artId -> inventoryRepository.find(artId))
            .map(inventoryEntity -> removeArticle(productsEntity,inventoryEntity))
            .forEach(inventoryEntity -> {
                if (inventoryEntity.getStock()<0) {
                    throw new RuntimeException("not enough stock");
                }
            });

    }

    private InventoryEntity removeArticle(ProductsEntity productsEntity, InventoryEntity inventoryEntity){
        productsEntity.getContainArticles().stream()
            .filter(contain -> contain.getInventory().getArtId().equals(inventoryEntity.getArtId()))
            .findFirst()
            .ifPresent(containArticleEntity -> inventoryEntity.setStock(inventoryEntity.getStock() - containArticleEntity.getAmountOf()));
        return inventoryEntity;
    }
}
