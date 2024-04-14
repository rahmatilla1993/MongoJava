package com.example.springbootadvanced.service;

import com.example.springbootadvanced.dto.BlogCreateDto;
import com.example.springbootadvanced.dto.UserCreateDto;
import com.example.springbootadvanced.entity.Blog;

import java.util.List;

public interface BlogService {
    List<Blog> getAll();

    Blog findById(long id);

    Blog create(BlogCreateDto dto);
    List<Blog> saveAll(List<BlogCreateDto> dtos);

    boolean edit(BlogCreateDto dto, long id);

    boolean delete(long id);
}
