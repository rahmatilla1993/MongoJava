package com.example.springbootadvanced.resource;

import com.example.springbootadvanced.dto.CommentCreateDto;
import com.example.springbootadvanced.dto.CommentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentResource {
    private final RestTemplate restTemplate;

    @Value("${comments.url.saveComments}")
    private String saveCommentsUrl;

    @Value("${comments.url.postComments}")
    private String postCommentsUrl;

    public List<CommentDto> getComments(long postId) {
        ResponseEntity<List<CommentDto>> commentsResponseEntity = restTemplate.exchange(
                postCommentsUrl,
                HttpMethod.GET,
                HttpEntity.EMPTY,
                new ParameterizedTypeReference<List<CommentDto>>() {
                },
                postId
        );
        return commentsResponseEntity.getBody();
    }

    public void saveComments(List<CommentCreateDto> dtos) {
        restTemplate.postForObject(saveCommentsUrl, dtos, Void.class);
    }
}
