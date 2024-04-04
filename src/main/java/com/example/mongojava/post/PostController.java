package com.example.mongojava.post;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostRepo postRepository;

    @GetMapping
    public HttpEntity<List<Post>> findAll() {
        return ResponseEntity.ok(postRepository.findAll());
    }

    @GetMapping("/{id}")
    public HttpEntity<Post> findById(@PathVariable("id") String id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found"));
        return ResponseEntity.ok(post);
    }

    @GetMapping("/byField")
    public HttpEntity<List<Post>> findAllByFieldStart(
            @RequestParam(name = "field", defaultValue = "title") String field,
            @RequestParam(name = "value") String value
    ) {
        return ResponseEntity.ok(
                postRepository.findAllByFieldStartsWith(field, value)
        );
    }

    @GetMapping("/pagedSort")
    public HttpEntity<Page<Post>> findAllByPageAndSort(
            @RequestParam(name = "field", defaultValue = "title") String field,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size,
            @RequestParam(name = "direction", defaultValue = "ASC", required = false) Sort.Direction direction
    ) {
        return ResponseEntity.ok(postRepository.findAll(page, size, field, direction));
    }
}
