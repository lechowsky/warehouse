package com.org.warehouse.business;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.org.warehouse.controller.dto.InventoryDTO;
import com.org.warehouse.controller.dto.Item;
import com.org.warehouse.repository.DataTypes;
import com.org.warehouse.repository.InventoryRepository;
import com.org.warehouse.repository.entity.InventoryEntity;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LoadCommandInventoryTest {

    private LoadCommandInventory sut;

    private ObjectMapper objectMapperMock;
    private RepositoryFactory factoryMock;
    private InventoryRepository repositoryMock;

    @BeforeEach
    void setUp() {
        objectMapperMock = mock(ObjectMapper.class);
        factoryMock = mock(RepositoryFactory.class);
        repositoryMock = mock(InventoryRepository.class);
        sut = new LoadCommandInventory(objectMapperMock, factoryMock);
    }

    @Test
    void isTypeTest() {
        assertTrue(sut.isType(DataTypes.ARTICLES));
    }

    @Test
    public void executeTest() throws JsonProcessingException {

        String payload = "{\"inventory\":[{\"art_id\":\"1\",\"name\":\"leg\",\"stock\":\"12\"},{\"art_id\":\"2\",\"name\":\"screw\",\"stock\":\"17\"},{\"art_id\":\"3\",\"name\":\"seat\",\"stock\":\"2\"},{\"art_id\":\"4\",\"name\":\"table top\",\"stock\":\"1\"}]}";
        InventoryDTO inventoryDto = buildInventory();

        when(objectMapperMock.readValue(payload, InventoryDTO.class))
            .thenReturn(inventoryDto);
        when(factoryMock.get(DataTypes.ARTICLES)).thenReturn(repositoryMock);
        InventoryEntity inventoryEntity = new InventoryEntity();
        when(repositoryMock.save(any(InventoryEntity.class)))
            .thenReturn(inventoryEntity);
        sut.execute(DataTypes.ARTICLES, payload);

        verify(factoryMock).get(DataTypes.ARTICLES);
        verify(objectMapperMock).readValue(payload, InventoryDTO.class);
        verify(repositoryMock).save(inventoryEntity);
    }

    private InventoryDTO buildInventory() {
        InventoryDTO inventoryDto = new InventoryDTO();
        List<Item> items = new ArrayList();
        items.add(new Item());
        inventoryDto.setInventory(items);
        return inventoryDto;
    }

}