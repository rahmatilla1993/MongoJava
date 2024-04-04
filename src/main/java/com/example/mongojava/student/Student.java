package com.example.mongojava.student;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "students")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Student {
    @Id
    private String id;
    private String name;
    private Integer age;
    private LocalDate birthDate;
    private Gender gender;
    private String groupId;
}
