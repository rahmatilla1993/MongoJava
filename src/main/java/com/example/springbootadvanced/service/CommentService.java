package com.example.springbootadvanced.service;

import com.example.springbootadvanced.dto.CommentCreateDto;
import com.example.springbootadvanced.entity.Blog;
import com.example.springbootadvanced.entity.Comment;

import java.util.List;

public interface CommentService {
    List<Comment> getAll();
    List<Comment> findAllByBlog(long blogId);

    Comment findById(long id);

    Comment create(CommentCreateDto dto);
    List<Comment> saveAll(List<CommentCreateDto> dtos);

    boolean edit(CommentCreateDto dto, long id);

    boolean delete(long id);

    void deleteCommentsByBlog(Blog blog);
}
