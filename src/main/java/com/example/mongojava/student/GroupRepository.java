package com.example.mongojava.student;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryRestResource(path = "groups", excerptProjection = GroupProjection.class)
public interface GroupRepository extends MongoRepository<Group, String> {
}
