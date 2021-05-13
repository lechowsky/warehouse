
package com.org.warehouse.business.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ContainArticle {

    @JsonProperty("art_id")
    public Integer artId;
    @JsonProperty("amount_of")
    public Integer amountOf;

}
