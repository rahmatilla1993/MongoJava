package com.example.springbootadvanced.event;

import com.example.springbootadvanced.entity.Blog;

public record BlogDeletedEvent(Blog blog) {
}
