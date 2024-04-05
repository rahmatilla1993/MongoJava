package com.example.springbootadvanced.resource;

import com.example.springbootadvanced.dto.CommentCreateDto;
import com.example.springbootadvanced.dto.CommentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentResource {

    @Value("${comments.url.saveComments}")
    private String saveCommentsUrl;

    @Value("${comments.url.postComments}")
    private String postCommentsUrl;

    public List<CommentDto> getComments(long postId) {
        WebClient webClient = WebClient.create();
        return webClient.get()
                .uri(postCommentsUrl, postId)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<CommentDto>>() {
                })
                .block();
    }

    public void saveComments(List<CommentCreateDto> dtos) {
        WebClient webClient = WebClient.create();
        webClient.post()
                .uri(saveCommentsUrl)
                .body(BodyInserters.fromValue(dtos))
                .retrieve()
                .bodyToMono(Void.class)
                .block();
    }
}
