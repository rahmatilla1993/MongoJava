package com.example.springbootadvanced.repository;

import com.example.springbootadvanced.entity.Blog;
import com.example.springbootadvanced.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findAllByBlog(Blog blog);
}
