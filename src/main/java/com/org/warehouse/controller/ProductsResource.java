package com.org.warehouse.controller;

import com.org.warehouse.business.Loader;
import com.org.warehouse.business.ProductBusiness;
import com.org.warehouse.controller.dto.LoadDTO;
import com.org.warehouse.repository.ProductsRepository;
import com.org.warehouse.repository.entity.ProductsEntity;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/warehouse")
public class ProductsResource {

    private Loader loader;
    private ProductsRepository productsRepository;
    private ProductBusiness productBusiness;

    public ProductsResource(Loader loader, ProductsRepository productsRepository,
        ProductBusiness productBusiness) {
        this.loader = loader;
        this.productsRepository = productsRepository;
        this.productBusiness = productBusiness;
    }

    @POST
    @Path("/load")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response load(LoadDTO loadDTO) {
        loader.load(loadDTO.getType());
        return Response.ok().build();
    }

    @GET
    @Path("/products")
    @Produces(MediaType.APPLICATION_JSON)
    public List<ProductsEntity> getAllProducts() {
        return productsRepository.findAll();
    }

    @POST
    @Path("/products/sell/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response sellProduct(@PathParam("id") Integer id) {
        try {
            productBusiness.sell(id);
        } catch (RuntimeException ex){
            return Response.noContent().build();
        }
        productBusiness.sell(id);
        return Response.ok().build();
    }

}