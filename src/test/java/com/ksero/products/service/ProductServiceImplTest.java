package com.ksero.products.service;

import com.ksero.products.domain.model.entity.Product;
import com.ksero.products.domain.persistence.ProductRepository;
import com.ksero.products.domain.service.ProductService;
import com.ksero.shared.exception.ResourceNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import javax.validation.Validator;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ProductServiceImplTest {

    Validator validator = mock(Validator.class);

    @Mock
    ProductRepository productRepository = mock(ProductRepository.class);

    @InjectMocks
    ProductService productService = new ProductServiceImpl(productRepository, validator);

    Product generateProduct(Long id){
        Product product = new Product(id,"name","description",100.50,1L);
        return product;
    }

    @Test
    void getAllProductListEmpty() {
        List<Product> expectedProducts = new ArrayList<>();

        when(productRepository.findAll()).thenReturn(expectedProducts);

        List<Product> resultProducts = productService.getAll();

        Assertions.assertEquals(expectedProducts, resultProducts);
    }

    @Test
    void getAllProductListWithContent() {
        List<Product> expectedProducts = new ArrayList<>();
        Product product1 = generateProduct(1L);
        Product product2 = generateProduct(2L);
        Product product3 = generateProduct(3L);

        expectedProducts.add(product1);
        expectedProducts.add(product2);
        expectedProducts.add(product3);

        when(productRepository.findAll()).thenReturn(expectedProducts);

        List<Product> resultProducts = productService.getAll();

        Assertions.assertEquals(expectedProducts, resultProducts);
    }

    @Test
    void getById() {
        Product expectedProduct = generateProduct(1L);

        when(productRepository.findById(1L)).thenReturn(Optional.ofNullable(expectedProduct));

        Product resultProduct = productService.getById(1L);

        Assertions.assertEquals(expectedProduct, resultProduct);
    }

    @Test
    void getByIdWhenProductNotExist() {
        Product expectedProduct = generateProduct(1L);

        when(productRepository.findById(1L)).thenReturn(Optional.ofNullable(null));

        Assertions.assertThrows(ResourceNotFoundException.class, ()->{
            productService.getById(1L);
        });
    }

    @Test
    void create() {
        Product expectedProduct = generateProduct(1L);

        when(productRepository.save(expectedProduct)).thenReturn(expectedProduct);

        Product resultProduct = productService.create(expectedProduct);

        Assertions.assertEquals(expectedProduct, resultProduct);
    }

    @Test
    void updateIfProductExist() {

        Product expectedProduct = generateProduct(1L);

        when(productRepository.save(expectedProduct)).thenReturn(expectedProduct);
        when(productRepository.findById(1L)).thenReturn(Optional.of(expectedProduct));
        when(productRepository.existsById(1L)).thenReturn(true);

        Product resultProduct = productService.update(1L ,expectedProduct);

        Assertions.assertEquals(expectedProduct, resultProduct);

    }

    @Test
    void updateIfProductNotExist() {

        Product expectedProduct = generateProduct(1L);

        when(productRepository.save(expectedProduct)).thenReturn(expectedProduct);
        when(productRepository.findById(1L)).thenReturn(Optional.ofNullable(null));
        when(productRepository.existsById(1L)).thenReturn(false);

        Assertions.assertThrows(ResourceNotFoundException.class, ()->{
            productService.update(1L ,expectedProduct);
        });
    }
}