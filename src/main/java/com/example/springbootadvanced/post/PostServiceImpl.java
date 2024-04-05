package com.example.springbootadvanced.post;

import com.example.springbootadvanced.dto.CommentCreateDto;
import com.example.springbootadvanced.dto.PostCreateDto;
import com.example.springbootadvanced.dto.PostDto;
import com.example.springbootadvanced.resource.CommentResource;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final CommentResource commentResource;

    @Override
    public List<PostDto> findAll() {
        return postRepository.findAll()
                .stream()
                .map(post -> PostDto.builder()
                        .body(post.getBody())
                        .title(post.getTitle())
                        .comments(Collections.emptyList())
                        .build()
                )
                .toList();
    }

    @Override
    public PostDto getOne(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found"));

        return PostDto.builder()
                .title(post.getTitle())
                .body(post.getBody())
                .comments(commentResource.getComments(post.getId()))
                .build();
    }

    @Override
    public PostDto save(PostCreateDto dto) {
        Post post = postRepository.save(
                Post.builder()
                        .title(dto.getTitle())
                        .body(dto.getBody())
                        .build()
        );
        return PostDto.builder()
                .title(post.getTitle())
                .body(post.getBody())
                .build();
    }

    @Override
    public void saveList(List<PostCreateDto> dtoList) {
        dtoList.forEach(dto -> postRepository
                .save(Post.builder()
                        .title(dto.getTitle())
                        .body(dto.getBody())
                        .build())
        );
    }

    @Override
    public void saveComments(List<CommentCreateDto> dtos) {
        commentResource.saveComments(dtos);
    }
}
