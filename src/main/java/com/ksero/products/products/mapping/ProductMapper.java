package com.ksero.products.products.mapping;

import com.ksero.products.products.domain.model.entity.Product;
import com.ksero.products.products.resources.CreateResource.CreateProductResource;
import com.ksero.products.products.resources.Resource.ProductResource;
import com.ksero.products.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;

public class ProductMapper implements Serializable {

    @Autowired
    EnhancedModelMapper mapper;

    public ProductResource toResource(Product model){
        return mapper.map(model, ProductResource.class);
    }

    public Product toModel(CreateProductResource resource){
        return mapper.map(resource, Product.class);
    }

    public List<ProductResource> modelListToResource(List<Product> modelList){
        return mapper.mapList(modelList, ProductResource.class);
    }

}
