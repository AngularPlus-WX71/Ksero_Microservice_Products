package com.ksero.products.mapping;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("Configuration")
public class MappingConfiguration {

    @Bean
    public ProductMapper productMapper(){return new ProductMapper();}

}
