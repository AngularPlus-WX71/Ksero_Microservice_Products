package com.ksero.products.api;

import com.ksero.products.domain.service.ProductService;
import com.ksero.products.mapping.ProductMapper;
import com.ksero.products.resources.CreateResource.CreateProductResource;
import com.ksero.products.resources.Resource.ProductResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", methods = {
        RequestMethod.GET,
        RequestMethod.POST,
        RequestMethod.PUT,
        RequestMethod.DELETE
})
@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    @Autowired
    private ProductService service;

    @Autowired
    private ProductMapper mapper;

    @GetMapping
    public List<ProductResource> getAll() {
        return mapper.modelListToResource(service.getAll());
    }

    @GetMapping("{Id}")
    public ProductResource getById(@PathVariable("Id") Long Id) {
        return mapper.toResource(service.getById(Id));
    }

    @GetMapping("userId/{Id}")
    public List<ProductResource> getByUserId(@PathVariable("Id") Long userId) {
        return mapper.modelListToResource(service.getAllByUserId(userId));
    }
    @PostMapping
    public ProductResource create(@RequestBody CreateProductResource request) {
        return mapper.toResource(service.create(mapper.toModel(request)));
    }

    @PutMapping("{Id}")
    public ProductResource update(@PathVariable("Id") Long Id, @RequestBody CreateProductResource request) {
        return mapper.toResource(service.update(Id, mapper.toModel(request)));
    }

    @DeleteMapping("{Id}")
    public ResponseEntity<?> delete(@PathVariable("Id") Long Id){
        return service.delete(Id);
    }

    @DeleteMapping("userId/{Id}")
    public ResponseEntity<?> deleteByUserId(@PathVariable("Id") Long userId){
        return service.deleteByUserId(userId);
    }
}
