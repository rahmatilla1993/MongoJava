package com.example.springbootadvanced.handler;

import com.example.springbootadvanced.entity.Blog;
import com.example.springbootadvanced.event.BlogDeletedEvent;
import com.example.springbootadvanced.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class BlogDeletedEventHandler {
    private final CommentService commentService;

    @EventListener({BlogDeletedEvent.class})
    public void blogDeleteEventHandle(BlogDeletedEvent event) {
        Blog blog = event.blog();
        commentService.deleteCommentsByBlog(blog);
        log.info("Delete comments by blog : {}", blog);
    }
}
