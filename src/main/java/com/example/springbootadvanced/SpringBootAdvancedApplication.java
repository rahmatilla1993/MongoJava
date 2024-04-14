package com.example.springbootadvanced;

import com.example.springbootadvanced.student.Student;
import com.example.springbootadvanced.student.StudentRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;

import java.io.File;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
@EnableCaching
public class SpringBootAdvancedApplication {
    private final String urlPath = Objects.requireNonNull(
                    SpringBootAdvancedApplication.class
                            .getClassLoader()
                            .getResource("students.json"))
            .getFile();

    public static void main(String[] args) {
        SpringApplication.run(SpringBootAdvancedApplication.class, args);
    }

    @Bean
    public Caffeine caffeineConfig() {
        return Caffeine.newBuilder()
                .expireAfterWrite(60, TimeUnit.MINUTES);
    }

    @Bean
    public CacheManager cacheManager(Caffeine caffeine) {
        var cacheManager = new CaffeineCacheManager("students");
        cacheManager.setCaffeine(caffeine);
        return cacheManager;
    }

    @Bean
    public ApplicationRunner applicationRunner(ObjectMapper mapper, StudentRepository studentRepository) {
        return (args -> {
            File file = new File(urlPath);
            List<Student> students = mapper.readValue(file, new TypeReference<>() {
            });
            studentRepository.saveAll(students);
        });
    }
}
