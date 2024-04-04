package com.example.mongojava.student;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;

    @Override
    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    @Override
    public Page<Student> findAllByPageable(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return studentRepository.findAll(pageable);
    }

    @Override
    public List<Student> findAllByGender(Gender gender) {
        return studentRepository.getAllByGender(gender);
    }

    @Override
    public List<Student> findAllByGroupId(String groupId) {
        return studentRepository.findAllByGroupId(groupId);
    }

    @Override
    public Optional<Student> findById(String id) {
        return studentRepository.findById(id);
    }

    @Override
    public List<Student> sortByField(String field, Sort.Direction direction) {
        Sort sort = Sort.by(direction, field);
        return studentRepository.findAll(sort);
    }

    @Override
    public Student save(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public boolean edit(Student student) {
        Student studentFromDb = studentRepository.findById(student.getId())
                .orElseThrow(() -> new RuntimeException("Student not found"));
        if (student.getName() != null) {
            studentFromDb.setName(student.getName());
        }
        if (student.getAge() != null) {
            studentFromDb.setAge(student.getAge());
        }
        if (student.getGender() != null) {
            studentFromDb.setGender(student.getGender());
        }
        if (student.getGroupId() != null) {
            studentFromDb.setGroupId(student.getGroupId());
        }
        if (student.getBirthDate() != null) {
            studentFromDb.setBirthDate(student.getBirthDate());
        }
        studentRepository.save(studentFromDb);
        return true;
    }

    @Override
    public boolean delete(Student student) {
        studentRepository.delete(student);
        return true;
    }
}
