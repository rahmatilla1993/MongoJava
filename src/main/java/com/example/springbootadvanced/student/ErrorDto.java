package com.example.springbootadvanced.student;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorDto {
    private int code;
    private String error;
    private String path;
    private LocalDateTime timestamp;
}
