package com.example.springbootadvanced.post;

import com.example.springbootadvanced.dto.CommentCreateDto;
import com.example.springbootadvanced.dto.PostCreateDto;
import com.example.springbootadvanced.dto.PostDto;

import java.util.List;

public interface PostService {
    List<PostDto> findAll();

    PostDto getOne(Long id);

    PostDto save(PostCreateDto dto);

    void saveList(List<PostCreateDto> dtoList);

    void saveComments(List<CommentCreateDto> dtos);
}
