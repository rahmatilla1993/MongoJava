package com.example.springbootadvanced.service;

import com.example.springbootadvanced.config.RestResponsePage;
import com.example.springbootadvanced.product.ProductCriteria;
import com.example.springbootadvanced.product.ProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Service("WebClientConsumerServiceImpl")
@RequiredArgsConstructor
public class WebClientConsumerServiceImpl implements ConsumerService {

    private final WebClient webClient;

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
        return webClient.get()
                .uri(uri)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<RestResponsePage<ProductDto>>() {
                })
                .block();
    }

    @Override
    public void saveAll(List<ProductDto> productDtos) {
        webClient.post()
                .uri(saveAllProductsURI)
                .body(BodyInserters.fromValue(productDtos))
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    @Override
    public void save(ProductDto dto) {
        webClient.post()
                .uri(createProductURI)
                .body(BodyInserters.fromValue(dto))
                .retrieve()
                .bodyToMono(ProductDto.class)
                .block();
    }

    @Override
    public ProductDto getOne(long id) {
        return webClient.get()
                .uri(getProductURI, id)
                .retrieve()
                .bodyToMono(ProductDto.class)
                .block();
    }

    @Override
    public void edit(ProductDto dto) {
        webClient.put()
                .uri(editProductURI)
                .body(BodyInserters.fromValue(dto))
                .retrieve()
                .bodyToMono(ProductDto.class)
                .block();
    }

    @Override
    public void delete(Long id) {
        webClient.delete()
                .uri(deleteProductURI)
                .retrieve()
                .bodyToMono(Void.class)
                .block();
    }
}
