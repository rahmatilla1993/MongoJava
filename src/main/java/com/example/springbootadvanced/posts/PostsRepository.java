package com.example.springbootadvanced.posts;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class PostsRepository {

    private final JdbcTemplate jdbcTemplate;

    public List<PostReadDto> findAll() {
        String sql = "select p.title, p.description, concat(a.firstname, ' ', a.lastname) author\n" +
                " from post p inner join author a on a.id = p.author_id";
        return jdbcTemplate.query(sql, (RowMapper<PostReadDto>) (rs, rowNum) -> PostReadDto.builder()
                .title(rs.getString("title"))
                .description(rs.getString("description"))
                .author(rs.getString("author"))
                .build()
        );
    }
}
