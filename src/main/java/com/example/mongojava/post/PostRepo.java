package com.example.mongojava.post;

import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

@Repository
@RequiredArgsConstructor
public class PostRepo {
    private final MongoTemplate mongoTemplate;

    public List<Post> findAll() {
        return mongoTemplate.findAll(Post.class);
    }

    public Page<Post> findAll(int page, int size, String field, Sort.Direction direction) {
        Sort sort = Sort.by(direction, field);
        Pageable pageable = PageRequest.of(page, size, sort);
        Query query = new Query().with(pageable);
        List<Post> posts = mongoTemplate.find(query, Post.class);
        return PageableExecutionUtils.getPage(
                posts,
                pageable,
                () -> mongoTemplate.count(new Query(), Post.class)
        );
    }

    public Optional<Post> findById(String id) {
        Criteria criteria = Criteria.where("_id").is(new ObjectId(id));
        Query query = new Query(criteria);
        return Optional.ofNullable(mongoTemplate.findOne(query, Post.class));
    }

    public List<Post> findAllByFieldStartsWith(String field, String value) {
        Criteria criteria = Criteria
                .where(field)
                .regex(Pattern.compile("^%s.*$".formatted(value)));
        Query query = new Query(criteria);
        return mongoTemplate.find(query, Post.class);
    }
}
