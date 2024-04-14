package com.example.springbootadvanced.student;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class StudentCreateDto {
    private String name;
    private int age;
}
