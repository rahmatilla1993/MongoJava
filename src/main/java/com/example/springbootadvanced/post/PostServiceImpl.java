package com.example.springbootadvanced.post;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostResource postResource;
    @Override
    public List<Post> findAll() {
        return postResource.findAll();
    }

    @Override
    public Post findById(int id) {
        return postResource.getPost(id);
    }

    @Override
    public Page<Post> findAll(int page, int size) {
        return postResource.findAll(page, size);
    }
}
