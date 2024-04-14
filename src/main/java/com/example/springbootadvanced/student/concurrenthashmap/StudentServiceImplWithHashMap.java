package com.example.springbootadvanced.student.concurrenthashmap;

import com.example.springbootadvanced.student.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Service("StudentServiceImplWithHashMap")
@RequiredArgsConstructor
public class StudentServiceImplWithHashMap implements StudentService {
    private final StudentRepository studentRepository;

    private final ConcurrentHashMap<Long, Student> cache = new ConcurrentHashMap<>();

    @Override
    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    @Override
    public Student findOne(long id) {
        Student cachedStudent = cache.get(id);
        if (cachedStudent != null) {
            return cachedStudent;
        }
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Student with '%d' id not found".formatted(id)));
        cache.put(id, student);
        return student;
    }

    @Override
    public Student save(StudentCreateDto dto) {
        Student student = Student.builder()
                .age(dto.getAge())
                .name(dto.getName())
                .build();
        Student savedStudent = studentRepository.save(student);
        cache.put(savedStudent.getId(), savedStudent);
        return savedStudent;
    }

    @Override
    public Student edit(StudentCreateDto dto, long id) {
        Student student = findOne(id);
        student.setAge(dto.getAge());
        student.setName(dto.getName());
        studentRepository.save(student);
        cache.put(id, student);
        return student;
    }

    @Override
    public boolean delete(long id) {
        studentRepository.deleteById(id);
        cache.remove(id);
        return true;
    }
}
