package com.org.warehouse.repository;

import com.org.warehouse.repository.entity.ContainArticleEntity;
import com.org.warehouse.repository.entity.InventoryEntity;
import com.org.warehouse.repository.entity.ProductsEntity;
import java.util.List;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Singleton
public class ProductsRepositoryImpl implements ProductsRepository {

    private EntityManager em;

    public ProductsRepositoryImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<ProductsEntity> findAll() {
        return em.createQuery("select P from Products P", ProductsEntity.class).getResultList();
    }


    @Transactional
    @Override
    public ProductsEntity save(ProductsEntity productsEntity) {
        productsEntity
            .getContainArticles().stream().forEach(e -> e.setInventory(em.find(InventoryEntity.class, e.getInventory().getArtId())));
        productsEntity.getContainArticles().stream().forEach(em::persist);
        return em.merge(productsEntity);
    }

    @Override
    public ProductsEntity find(Integer id) {
        return em.find(ProductsEntity.class, id);
    }


}
