package com.example.mongojava.post;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "posts")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Post {
    @Id
    private String postId;
    private Integer id;
    private Integer userId;
    private String title;
    private String body;
}
