package com.example.springbootadvanced.student.concurrenthashmap;

import com.example.springbootadvanced.student.*;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("StudentServiceImplWithCashing")
@RequiredArgsConstructor
public class StudentServiceImplWithCashing implements StudentService {
    private final StudentRepository studentRepository;

    @Override
    @Cacheable(value = "students", key = "#root.methodName")
    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    @Override
    @Cacheable(value = "students", key = "#id")
    public Student findOne(long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Student with '%d' id not found".formatted(id)));
    }

    @Override
    public Student save(StudentCreateDto dto) {
        Student student = Student.builder()
                .age(dto.getAge())
                .name(dto.getName())
                .build();
        return studentRepository.save(student);
    }

    @Override
    @CachePut(value = "students", key = "#id")
    public Student edit(StudentCreateDto dto, long id) {
        Student student = findOne(id);
        student.setAge(dto.getAge());
        student.setName(dto.getName());
        studentRepository.save(student);
        return student;
    }

    @Override
    @CacheEvict(value = "students", key = "#id")
    public boolean delete(long id) {
        studentRepository.deleteById(id);
        return true;
    }
}
