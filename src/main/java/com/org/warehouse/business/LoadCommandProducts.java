package com.org.warehouse.business;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.org.warehouse.controller.dto.ProductDTO;
import com.org.warehouse.repository.DataTypes;
import com.org.warehouse.repository.entity.ContainArticleEntity;
import com.org.warehouse.repository.entity.InventoryEntity;
import com.org.warehouse.repository.entity.ProductsEntity;
import java.util.List;
import java.util.stream.Collectors;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Singleton;

@ApplicationScoped
public class LoadCommandProducts implements LoadCommand {

    private ObjectMapper mapper;
    private RepositoryFactory factory;

    public LoadCommandProducts(ObjectMapper mapper, RepositoryFactory factory) {
        this.mapper = mapper;
        this.factory = factory;
    }

    @Override
    public boolean isType(DataTypes dataTypes) {
        return DataTypes.PRODUCTS.equals(dataTypes);
    }

    @Override
    public void execute(DataTypes dataType, String payload) {
        ProductDTO productDTO = convert(payload);
        List<ProductsEntity> items = map(productDTO);
        items.stream().map(e -> factory.get(dataType).save(e)).collect(Collectors.toList());
    }

    private ProductDTO convert(String payload){
        try {
            return this.mapper.readValue(payload, ProductDTO.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private List<ProductsEntity> map(ProductDTO productDTO) {
        return productDTO.getProducts().stream().map(e -> {
                ProductsEntity productsEntity =  new ProductsEntity();
                productsEntity.setName(e.getName());
                productsEntity.setContainArticles(
                    e.getContainArticles().stream().map(containArticle -> {
                        ContainArticleEntity containArticleEntity = new ContainArticleEntity();
                        containArticleEntity.setAmountOf(containArticle.getAmountOf());
                        containArticleEntity.setInventory(buildInventory(containArticle.getArtId()));
                        return containArticleEntity;
                        }

                    ).collect(Collectors.toSet())
                );

                return productsEntity;
            }
        ).collect(Collectors.toList());
    }

    private InventoryEntity buildInventory(Integer artId) {
        InventoryEntity inventoryEntity = new InventoryEntity();
        inventoryEntity.setArtId(artId);
        return inventoryEntity;
    }
}
