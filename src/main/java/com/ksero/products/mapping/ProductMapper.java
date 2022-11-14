package com.ksero.products.mapping;

import com.ksero.products.domain.model.entity.Product;
import com.ksero.products.resources.CreateResource.CreateProductResource;
import com.ksero.products.resources.Resource.ProductResource;
import com.ksero.shared.mapping.EnhancedModelMapper;
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
