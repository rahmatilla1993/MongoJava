package com.example.springbootadvanced.service;

import com.example.springbootadvanced.product.ProductCriteria;
import com.example.springbootadvanced.product.ProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Service(value = "RestTemplateConsumerServiceImpl")
@RequiredArgsConstructor
public class RestTemplateConsumerServiceImpl implements ConsumerService {
    private final RestTemplate restTemplate;

    @Value("${products.url.get}")
    public String getProductURI;

    @Value("${products.url.list}")
    public String getProductListURI;

    @Value("${products.url.saveAll}")
    public String saveAllProductsURI;

    @Value("${products.url.create}")
    public String createProductURI;

    @Value("${products.url.edit}")
    public String editProductURI;

    @Value("${products.url.delete}")
    public String deleteProductURI;

    @Override
    public Page<ProductDto> findAll(ProductCriteria productCriteria) {
        URI uri = UriComponentsBuilder.fromUriString(getProductListURI)
                .queryParam("page", productCriteria.getPage())
                .queryParam("size", productCriteria.getSize())
                .build()
                .toUri();
        ResponseEntity<Page<ProductDto>> pageResponseEntity = restTemplate.exchange(
                uri,
                HttpMethod.GET,
                HttpEntity.EMPTY,
                new ParameterizedTypeReference<Page<ProductDto>>() {
                }
        );
        return pageResponseEntity.getBody();
    }

    @Override
    public void saveAll(List<ProductDto> productDtos) {
        restTemplate.postForObject(saveAllProductsURI, productDtos, String.class);
    }

    @Override
    public void save(ProductDto dto) {
        restTemplate.postForObject(createProductURI, dto, Void.class);
    }

    @Override
    public ProductDto getOne(long id) {
        return restTemplate.getForObject(getProductURI, ProductDto.class, id);
    }

    @Override
    public void edit(ProductDto dto) {
        restTemplate.put(editProductURI, dto);
    }

    @Override
    public void delete(Long id) {
        restTemplate.delete(deleteProductURI, id);
    }
}
