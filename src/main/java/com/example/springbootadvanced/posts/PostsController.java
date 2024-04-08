package com.example.springbootadvanced.posts;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/post")
@RequiredArgsConstructor
public class PostsController {
    private final PostsRepository postsRepository;

    @GetMapping
    public HttpEntity<List<PostReadDto>> getAll() {
        return ResponseEntity.ok(postsRepository.findAll());
    }
}
