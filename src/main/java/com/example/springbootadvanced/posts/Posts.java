package com.example.springbootadvanced.posts;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Posts {
    private Long id;
    private String title;
    private String description;
    private Long authorId;
}
