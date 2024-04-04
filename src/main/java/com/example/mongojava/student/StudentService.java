package com.example.mongojava.student;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

public interface StudentService {

    List<Student> findAll();

    Page<Student> findAllByPageable(int page, int size);

    List<Student> findAllByGender(Gender gender);

    List<Student> findAllByGroupId(String groupId);

    List<Student> sortByField(String field, Sort.Direction direction);

    Optional<Student> findById(String id);

    Student save(Student student);

    boolean edit(Student student);

    boolean delete(Student student);
}
