package com.example.springbootadvanced.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class CommentCreateDto {
    private String author;
    private String message;
    private long blogId;
}
