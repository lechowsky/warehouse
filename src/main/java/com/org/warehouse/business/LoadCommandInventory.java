package com.org.warehouse.business;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.org.warehouse.controller.dto.InventoryDTO;
import com.org.warehouse.repository.DataTypes;
import com.org.warehouse.repository.entity.InventoryEntity;
import java.util.List;
import java.util.stream.Collectors;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class LoadCommandInventory implements LoadCommand {

    private ObjectMapper mapper;
    private RepositoryFactory factory;

    public LoadCommandInventory(ObjectMapper mapper, RepositoryFactory factory) {
        this.mapper = mapper;
        this.factory = factory;
    }

    @Override
    public boolean isType(DataTypes dataTypes) {
        return DataTypes.ARTICLES.equals(dataTypes);
    }

    @Override
    public void execute(DataTypes dataType, String payload) {
        InventoryDTO inventoryDTO = convert(payload);
        List<InventoryEntity> items = map(inventoryDTO);
        items.stream().map(e -> factory.get(dataType).save(e)).collect(Collectors.toList());
    }


    private InventoryDTO convert(String payload){
        try {
            return this.mapper.readValue(payload, InventoryDTO.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    };

    private List<InventoryEntity> map(InventoryDTO inventoryDTO) {
        return inventoryDTO.getInventory().stream().map(e -> {
                    InventoryEntity inventoryEntity =  new InventoryEntity();
                    inventoryEntity.setArtId(e.getArtId());
                    inventoryEntity.setName(e.getName());
                    inventoryEntity.setStock(e.getStock());
                    return inventoryEntity;
                }
        ).collect(Collectors.toList());
    }

}
