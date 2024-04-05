package com.example.springbootadvanced.dto;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentDto {
    private int id;
    private String message;
    private long postId;
}
