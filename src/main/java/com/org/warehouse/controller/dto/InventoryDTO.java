package com.org.warehouse.controller.dto;

import java.util.List;
import lombok.Data;

@Data
public class InventoryDTO {

    private List<Item> inventory;
}
