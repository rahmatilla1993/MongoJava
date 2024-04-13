package com.example.springbootadvanced.user;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserCreateDto {

    private String username;
    private String email;
    private String password;
}
