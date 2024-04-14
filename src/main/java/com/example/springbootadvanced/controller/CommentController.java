package com.example.springbootadvanced.controller;

import com.example.springbootadvanced.dto.CommentCreateDto;
import com.example.springbootadvanced.entity.Comment;
import com.example.springbootadvanced.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @GetMapping
    public HttpEntity<List<Comment>> getAll() {
        return ResponseEntity.ok(commentService.getAll());
    }

    @GetMapping("/{id}/byBlog")
    public HttpEntity<List<Comment>> findAllByBlog(@PathVariable("id") long id) {
        return ResponseEntity.ok(commentService.findAllByBlog(id));
    }

    @PostMapping
    public HttpEntity<Comment> save(@RequestBody CommentCreateDto dto) {
        return ResponseEntity.ok(commentService.create(dto));
    }

    @PostMapping("/saveAll")
    public HttpEntity<List<Comment>> saveAll(@RequestBody List<CommentCreateDto> dtos) {
        return ResponseEntity.ok(commentService.saveAll(dtos));
    }

    @PutMapping("/{id}")
    public HttpEntity<Boolean> edit(@PathVariable("id") long id, @RequestBody CommentCreateDto dto) {
        return ResponseEntity.ok(commentService.edit(dto, id));
    }

    @DeleteMapping("/{id}")
    public HttpEntity<Boolean> delete(@PathVariable("id") long id) {
        return ResponseEntity.ok(commentService.delete(id));
    }
}
