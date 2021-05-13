package com.org.warehouse.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Item {

    @JsonProperty("art_id")
    private Integer artId;
    private String name;
    private Integer stock;
}
