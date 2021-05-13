
package com.org.warehouse.business.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;

@Data
public class Product {

    public String name;
    @JsonProperty("contain_articles")
    public List<ContainArticle> containArticles;

}
