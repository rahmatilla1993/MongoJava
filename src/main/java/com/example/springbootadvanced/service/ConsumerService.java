package com.example.springbootadvanced.service;

import com.example.springbootadvanced.product.ProductCriteria;
import com.example.springbootadvanced.product.ProductDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ConsumerService {

    Page<ProductDto> findAll(ProductCriteria productCriteria);
    void saveAll(List<ProductDto> productDtos);
    void save(ProductDto dto);
    ProductDto getOne(long id);
    void edit(ProductDto dto);
    void delete(Long id);
}
