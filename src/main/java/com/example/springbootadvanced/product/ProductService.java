package com.example.springbootadvanced.product;

import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {
    Page<ProductDto> findAll(ProductCriteria productCriteria);
    ProductDto getOne(Long id);
    void saveAll(List<ProductDto> productDtos);
    void save(ProductDto dto);
    void edit(ProductDto dto);
    void delete(Long id);
}
