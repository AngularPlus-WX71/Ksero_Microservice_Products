package com.ksero.products.products.service;

import com.ksero.products.products.domain.model.entity.Product;
import com.ksero.products.products.domain.persistence.ProductRepository;
import com.ksero.products.products.domain.service.ProductService;
import com.ksero.products.shared.exception.ResourceNotFoundException;
import com.ksero.products.shared.exception.ResourceValidationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;

@Service
public class ProductServiceImpl implements ProductService {

    private static final String ENTITY="Product";
    private final ProductRepository repository;
    private final Validator validator;

    public ProductServiceImpl(ProductRepository repository, Validator validator) {
        this.repository = repository;
        this.validator = validator;
    }

    @Override
    public List<Product> getAll() {
        return repository.findAll();
    }

    @Override
    public Product getById(Long id) {
        return repository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException(ENTITY, id));
    }

    @Override
    public Product create(Product request) {
        Set<ConstraintViolation<Product>> violations = validator.validate(request);

        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        return repository.save(request);
    }

    @Override
    public Product update(Long id, Product request) {
        Set<ConstraintViolation<Product>> violations = validator.validate(request);

        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        if (!repository.existsById(id))
            throw new ResourceNotFoundException(ENTITY, id);

        return repository.findById(id).map(
                product -> repository.save(request
                        .withName(request.getName())
                        .withDescription(request.getDescription())
                        .withPrice(request.getPrice())
                )).orElseThrow(
                ()->new ResourceNotFoundException(ENTITY, id)
        );
    }

    @Override
    public ResponseEntity<?> delete(Long id) {
        return repository.findById(id).map(
                        product -> {
                            repository.delete(product);
                            return ResponseEntity.ok().build();
                        })
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, id));
    }
}
