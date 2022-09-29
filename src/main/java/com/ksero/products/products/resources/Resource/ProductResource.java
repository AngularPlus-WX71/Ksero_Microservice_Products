package com.ksero.products.products.resources.Resource;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ProductResource {
    private Long id;
    private String name;
    private String description;
    private Double price;
}
