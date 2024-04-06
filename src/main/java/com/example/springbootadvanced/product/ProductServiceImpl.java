package com.example.springbootadvanced.product;

import com.example.springbootadvanced.service.ConsumerService;
import com.example.springbootadvanced.service.OpenFeignClientConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private final ConsumerService consumerService;

    @Autowired
    public ProductServiceImpl(@Qualifier("WebClientConsumerServiceImpl") ConsumerService consumerService) {
        this.consumerService = consumerService;
    }

    @Override
    public Page<ProductDto> findAll(ProductCriteria productCriteria) {
        return consumerService.findAll(productCriteria);
    }

    @Override
    public ProductDto getOne(Long id) {
        return consumerService.getOne(id);
    }

    @Override
    public void saveAll(List<ProductDto> productDtos) {
        consumerService.saveAll(productDtos);
    }

    @Override
    public void save(ProductDto dto) {
        consumerService.save(dto);
    }

    @Override
    public void edit(ProductDto dto) {
        consumerService.edit(dto);
    }

    @Override
    public void delete(Long id) {
        consumerService.delete(id);
    }
}
