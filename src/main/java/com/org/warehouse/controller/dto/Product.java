
package com.org.warehouse.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.quarkus.runtime.annotations.RegisterForReflection;
import java.util.List;
import lombok.Data;

@Data
@RegisterForReflection
public class Product {

    public String name;
    @JsonProperty("contain_articles")
    public List<ContainArticle> containArticles;

}
