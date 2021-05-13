
package com.org.warehouse.controller.dto;

import io.quarkus.runtime.annotations.RegisterForReflection;
import java.util.List;
import lombok.Data;

@Data
@RegisterForReflection
public class ProductDTO {

    public List<Product> products;

}
