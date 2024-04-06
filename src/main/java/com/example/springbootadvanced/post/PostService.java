package com.example.springbootadvanced.post;

import org.springframework.data.domain.Page;

import java.util.List;

public interface PostService {

    List<Post> findAll();

    Post findById(int id);

    Page<Post> findAll(int page, int size);
}
