package com.org.warehouse.repository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.org.warehouse.repository.entity.ContainArticleEntity;
import com.org.warehouse.repository.entity.InventoryEntity;
import com.org.warehouse.repository.entity.ProductsEntity;
import java.util.EnumMap;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.ErrorManager;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class ProductsRepositoryImplTest {

    private ProductsRepositoryImpl sut;
    private EntityManager emMock;

    @BeforeEach
    public void setUp(){
        emMock = Mockito.mock(EntityManager.class);
        sut = new ProductsRepositoryImpl(emMock);
    }

    @Test
    void findAllTest() {
        ProductsEntity productsEntity =  buildProductEntity();

        when(emMock.merge(productsEntity)).thenReturn(productsEntity);
        when(emMock.find(InventoryEntity.class, 1))
            .thenReturn(productsEntity.getContainArticles()
                .stream().findFirst().get().getInventory());
        doNothing().when(emMock).persist(any());

        assertEquals(sut.save(productsEntity), productsEntity );
    }

    private ProductsEntity buildProductEntity() {
        ProductsEntity productsEntity = new ProductsEntity();
        Set<ContainArticleEntity> containerArticles = new HashSet();
        ContainArticleEntity containerArticle = new ContainArticleEntity();
        InventoryEntity inventory =  new InventoryEntity();
        inventory.setArtId(1);
        containerArticle.setInventory(inventory);
        containerArticles.add(containerArticle);
        productsEntity.setContainArticles(containerArticles);
        return productsEntity;
    }

    @Test
    void saveTest() {

    }
}