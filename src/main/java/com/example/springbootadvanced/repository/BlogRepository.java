package com.example.springbootadvanced.repository;

import com.example.springbootadvanced.entity.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface BlogRepository extends JpaRepository<Blog, Long> {
}
