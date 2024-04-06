package com.example.springbootadvanced.product;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping()
    public HttpEntity<Page<ProductDto>> findAll(ProductCriteria productCriteria) {
        return ResponseEntity.ok(productService.findAll(productCriteria));
    }

    @GetMapping("/{id}")
    public HttpEntity<ProductDto> getOne(@PathVariable("id") long id) {
        return ResponseEntity.ok(productService.getOne(id));
    }

    @PostMapping("/saveAll")
    public HttpEntity<Void> saveAll(@RequestBody List<ProductDto> productDtos) {
        productService.saveAll(productDtos);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/save")
    public HttpEntity<Void> save(@RequestBody ProductDto dto) {
        productService.save(dto);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/edit")
    public HttpEntity<Void> edit(@RequestBody ProductDto dto) {
        productService.edit(dto);
        return ResponseEntity.noContent().build();
    }


    @DeleteMapping("/{id}")
    public HttpEntity<Void> delete(@PathVariable("id") long id) {
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
