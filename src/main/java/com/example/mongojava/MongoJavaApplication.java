package com.example.mongojava;

import com.example.mongojava.post.Post;
import com.example.mongojava.post.PostRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.net.URL;
import java.util.List;

@SpringBootApplication
public class MongoJavaApplication {

    public static void main(String[] args) {
        SpringApplication.run(MongoJavaApplication.class, args);
    }

//    @Bean
    public ApplicationRunner applicationRunner(ObjectMapper objectMapper,
                                               PostRepository postRepository) {
        return (args -> {
            URL url = new URL("https://jsonplaceholder.typicode.com/posts");
            List<Post> posts = objectMapper.readValue(url, new TypeReference<>() {
            });
            postRepository.saveAll(posts);
        });
    }
}
