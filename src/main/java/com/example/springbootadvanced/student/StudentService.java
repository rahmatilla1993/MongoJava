package com.example.springbootadvanced.student;

import java.util.List;

public interface StudentService {
    List<Student> findAll();
    Student findOne(long id);
    Student save(StudentCreateDto dto);
    Student edit(StudentCreateDto dto, long id);
    boolean delete(long id);
}
