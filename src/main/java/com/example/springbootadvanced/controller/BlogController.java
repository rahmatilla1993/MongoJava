package com.example.springbootadvanced.controller;

import com.example.springbootadvanced.dto.BlogCreateDto;
import com.example.springbootadvanced.entity.Blog;
import com.example.springbootadvanced.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/blogs")
@RequiredArgsConstructor
public class BlogController {
    private final BlogService blogService;

    @GetMapping
    public HttpEntity<List<Blog>> getAll() {
        return ResponseEntity.ok(blogService.getAll());
    }

    @PostMapping
    public HttpEntity<Blog> save(@RequestBody BlogCreateDto dto) {
        return ResponseEntity.ok(blogService.create(dto));
    }

    @PostMapping("/saveAll")
    public HttpEntity<List<Blog>> saveAll(@RequestBody List<BlogCreateDto> dtos) {
        return ResponseEntity.ok(blogService.saveAll(dtos));
    }

    @PutMapping("/{id}")
    public HttpEntity<Boolean> edit(@RequestBody BlogCreateDto dto, @PathVariable("id") long id) {
        return ResponseEntity.ok(blogService.edit(dto, id));
    }

    @DeleteMapping("/{id}")
    public HttpEntity<Boolean> delete(@PathVariable("id") long id) {
        return ResponseEntity.ok(blogService.delete(id));
    }
}
