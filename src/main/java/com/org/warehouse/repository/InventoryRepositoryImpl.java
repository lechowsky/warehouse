package com.org.warehouse.repository;

import com.org.warehouse.repository.entity.InventoryEntity;
import java.util.List;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Singleton
public class InventoryRepositoryImpl  implements InventoryRepository {

    EntityManager em;

    public InventoryRepositoryImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<InventoryEntity> findAll() {
        return em.createQuery("select I from Inventory I", InventoryEntity.class).getResultList();
    }

    @Override
    @Transactional
    public InventoryEntity save(InventoryEntity entity) {
        return em.merge(entity);
    }

    @Override
    public InventoryEntity find(Integer id) {
        return em.find(InventoryEntity.class, id);
    }
}
