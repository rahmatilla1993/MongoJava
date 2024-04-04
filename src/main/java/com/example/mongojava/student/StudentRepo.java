package com.example.mongojava.student;

import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class StudentRepo {
    private final MongoTemplate mongoTemplate;

    public List<Student> findAll() {
        return mongoTemplate.findAll(Student.class);
    }

    public Optional<Student> findById(String id) {
        Criteria criteria = Criteria.where("_id").is(new ObjectId(id));
        Query query = new Query(criteria);
        return Optional.ofNullable(mongoTemplate.findOne(query, Student.class));
    }

    public List<Student> findAllByGender(Gender gender) {
        Query query = new Query(Criteria.where("gender").is(gender));
        return mongoTemplate.find(query, Student.class);
    }

    public Page<Student> findAllByPageable(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Query query = new Query().with(pageable);
        List<Student> students = mongoTemplate.find(query, Student.class);
        return PageableExecutionUtils.getPage(
                students,
                pageable,
                () -> mongoTemplate.count(new Query(), Student.class)
        );
    }

    public List<Student> findAllByGroupId(String groupId) {
        Criteria criteria = Criteria.where("groupId").is(groupId);
        Query query = new Query(criteria);
        return mongoTemplate.find(query, Student.class);
    }

    public List<Student> sortByField(String field, Sort.Direction direction) {
        Sort sort = Sort.by(direction, field);
        Query query = new Query().with(sort);
        return mongoTemplate.find(query, Student.class);
    }

    public Student save(Student student) {
        return mongoTemplate.save(student);
    }

    public boolean edit(Student student) {
        Criteria criteria = Criteria.where("_id").is(new ObjectId(student.getId()));
        Query query = new Query(criteria);
        Update update = new Update();
        if (student.getName() != null) {
            update.set("name", (student.getName()));
        }
        if (student.getAge() != null) {
            update.set("age", (student.getAge()));
        }
        if (student.getGender() != null) {
            update.set("gender", (student.getGender()));
        }
        if (student.getGroupId() != null) {
            update.set("groupId", (student.getGroupId()));
        }
        if (student.getBirthDate() != null) {
            update.set("birthDate", (student.getBirthDate()));
        }
        return mongoTemplate.updateFirst(query, update, Student.class).wasAcknowledged();
    }

    public boolean delete(String id) {
        Criteria criteria = Criteria.where("_id").is(new ObjectId(id));
        Query query = new Query(criteria);
        return mongoTemplate.remove(query, Student.class).wasAcknowledged();
    }
}
