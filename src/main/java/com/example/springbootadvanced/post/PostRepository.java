package com.example.springbootadvanced.post;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class PostRepository {
    private final JdbcTemplate jdbcTemplate;

    public List<Post> findAll() {
        String sql = "select * from posts;";
        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Post.class));
    }
}
