package com.example.springbootadvanced.post;

import com.example.springbootadvanced.dto.CommentCreateDto;
import com.example.springbootadvanced.dto.PostCreateDto;
import com.example.springbootadvanced.dto.PostDto;
import lombok.RequiredArgsConstructor;
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
    public HttpEntity<List<PostDto>> findAll() {
        return ResponseEntity.ok(postService.findAll());
    }

    @GetMapping("/{id}")
    public HttpEntity<PostDto> findById(@PathVariable("id") long id) {
        return ResponseEntity.ok(postService.getOne(id));
    }

    @PostMapping
    public HttpEntity<PostDto> save(@RequestBody PostCreateDto postCreateDto) {
        return ResponseEntity.ok(postService.save(postCreateDto));
    }

    @PostMapping("/saveList")
    public HttpEntity<String> saveList(@RequestBody List<PostCreateDto> dtoList) {
        postService.saveList(dtoList);
        return ResponseEntity.ok("Posts lists saved");
    }

    @PostMapping("/comments")
    public HttpEntity<Void> saveComment(@RequestBody List<CommentCreateDto> dtos) {
        postService.saveComments(dtos);
        return ResponseEntity.noContent().build();
    }
}
