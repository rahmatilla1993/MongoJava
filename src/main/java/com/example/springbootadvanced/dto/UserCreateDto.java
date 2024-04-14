package com.example.springbootadvanced.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class UserCreateDto {
    private String username;
    private String password;
}
