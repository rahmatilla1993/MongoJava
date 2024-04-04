package com.example.mongojava.student;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RepositoryRestResource(exported = false)
public interface StudentRepository extends MongoRepository<Student, String> {

    @Query("{gender: {$eq : '?0'}}")
    List<Student> getAllByGender(Gender gender);

    List<Student> findAllByGroupId(String id);
}
