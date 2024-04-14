package com.example.springbootadvanced.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class BlogCreateDto {
    private String title;
    private String description;
}
