package com.example.springbootadvanced.service;

import com.example.springbootadvanced.product.ProductCriteria;
import com.example.springbootadvanced.product.ProductDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(value = "OpenFeignClientConsumerService", url = "${products.url.base}")
public interface OpenFeignClientConsumerService extends ConsumerService {
    @Override
    @GetMapping("/list")
    Page<ProductDto> findAll(@SpringQueryMap ProductCriteria productCriteria);
    @Override
    @PostMapping("/saveAll") void saveAll(@RequestBody List<ProductDto> productDtos);
    @Override
    @PostMapping("/create")
    void save(@RequestBody ProductDto dto);
    @Override
    @GetMapping("/get/{id}")
    ProductDto getOne(@PathVariable("id") long id);
    @Override
    @PutMapping("/update")
    void edit(@RequestBody ProductDto dto);

    @Override
    @DeleteMapping("/delete/{id}")
    void delete(@PathVariable("id") Long id);
}
