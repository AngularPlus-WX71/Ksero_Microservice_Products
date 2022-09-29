package com.ksero.products.products.domain.service;

import com.ksero.products.products.domain.model.entity.Product;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductService {
    List<Product> getAll();
    Product getById(Long id);
    Product create(Product request);
    Product update(Long id, Product request);
    ResponseEntity<?> delete(Long id);
}
