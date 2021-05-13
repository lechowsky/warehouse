package com.org.warehouse.business;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.org.warehouse.controller.dto.ContainArticle;
import com.org.warehouse.controller.dto.InventoryDTO;
import com.org.warehouse.controller.dto.Item;
import com.org.warehouse.controller.dto.Product;
import com.org.warehouse.controller.dto.ProductDTO;
import com.org.warehouse.repository.DataTypes;
import com.org.warehouse.repository.InventoryRepository;
import com.org.warehouse.repository.ProductsRepository;
import com.org.warehouse.repository.entity.ContainArticleEntity;
import com.org.warehouse.repository.entity.InventoryEntity;
import com.org.warehouse.repository.entity.ProductsEntity;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LoadCommandProductsTest {

    private LoadCommandProducts sut;

    private ObjectMapper objectMapperMock;
    private RepositoryFactory factoryMock;
    private ProductsRepository repositoryMock;

    @BeforeEach
    void setUp() {
        objectMapperMock = mock(ObjectMapper.class);
        factoryMock = mock(RepositoryFactory.class);
        repositoryMock = mock(ProductsRepository.class);
        sut = new LoadCommandProducts(objectMapperMock, factoryMock);
    }

    @Test
    void isTypeTest() {
        assertTrue(sut.isType(DataTypes.PRODUCTS));
    }

    @Test
    public void executeTest() throws JsonProcessingException {

        String payload = "{\"products\":[{\"name\":\"Dining Chair\",\"contain_articles\":[{\"art_id\":\"1\",\"amount_of\":\"4\"},{\"art_id\":\"2\",\"amount_of\":\"8\"},{\"art_id\":\"3\",\"amount_of\":\"1\"}]},{\"name\":\"Dinning Table\",\"contain_articles\":[{\"art_id\":\"1\",\"amount_of\":\"4\"},{\"art_id\":\"2\",\"amount_of\":\"8\"},{\"art_id\":\"4\",\"amount_of\":\"1\"}]}]}";
        ProductDTO productDTO = buildProducts();

        when(objectMapperMock.readValue(payload, ProductDTO.class))
            .thenReturn(productDTO);
        when(factoryMock.get(DataTypes.PRODUCTS)).thenReturn(repositoryMock);
        ProductsEntity productsEntity = buildProductEntiry();
        when(repositoryMock.save(any(ProductsEntity.class)))
            .thenReturn(productsEntity);
        sut.execute(DataTypes.PRODUCTS, payload);

        verify(factoryMock).get(DataTypes.PRODUCTS);
        verify(objectMapperMock).readValue(payload, ProductDTO.class);
        verify(repositoryMock).save(productsEntity);
    }

    private ProductsEntity buildProductEntiry() {
        ProductsEntity productsEntity = new ProductsEntity();
        Set<ContainArticleEntity> containerArticles =  new HashSet();
        ContainArticleEntity containerArticle = new ContainArticleEntity();
        containerArticle.setInventory(new InventoryEntity());
        containerArticles.add(containerArticle);
        productsEntity.setContainArticles(containerArticles);
        return productsEntity;
    }

    private ProductDTO buildProducts() {
        ProductDTO productDTO =  new ProductDTO();
        List<Product> products = new ArrayList();
        Product product = new Product();
        List<ContainArticle> containerArticles =  new ArrayList();
        ContainArticle containerArticle = new ContainArticle();
        containerArticles.add(containerArticle);
        product.setContainArticles(containerArticles);
        products.add(product);
        productDTO.setProducts(products);
        return productDTO;
    }

}