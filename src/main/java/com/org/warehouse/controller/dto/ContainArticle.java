
package com.org.warehouse.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.Data;

@Data
@RegisterForReflection
public class ContainArticle {

    @JsonProperty("art_id")
    public Integer artId;
    @JsonProperty("amount_of")
    public Integer amountOf;

}
