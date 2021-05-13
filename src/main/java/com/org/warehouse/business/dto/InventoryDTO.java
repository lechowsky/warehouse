package com.org.warehouse.business.dto;

import io.quarkus.runtime.annotations.RegisterForReflection;
import java.util.List;
import lombok.Data;

@Data
@RegisterForReflection
public class InventoryDTO {

    private List<Item> inventory;
}
