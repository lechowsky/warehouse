package com.org.warehouse.business.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.Data;

@Data
@RegisterForReflection
public class Item {

    @JsonProperty("art_id")
    private Integer artId;
    private String name;
    private Integer stock;
}
