package com.example.springbootadvanced.post;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping
    public HttpEntity<List<Post>> findAll() {
        return ResponseEntity.ok(postService.findAll());
    }

    @GetMapping("/byPaged")
    public HttpEntity<Page<Post>> withPage(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "5") int size
    ) {
        return ResponseEntity.ok(postService.findAll(page, size));
    }

    @GetMapping("/{id}")
    public HttpEntity<Post> findOne(@PathVariable("id") int id) {
        return ResponseEntity.ok(postService.findById(id));
    }
}
