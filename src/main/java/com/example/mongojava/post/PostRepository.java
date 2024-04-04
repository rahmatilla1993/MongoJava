package com.example.mongojava.post;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {

    @Query("{title : {$regex: '^?0.*$'}}")
    List<Post> findAllByTitleStart(String start);

    List<Post> findAllByTitleStartingWithAndUserIdGreaterThan(String title, Integer userID);
}
