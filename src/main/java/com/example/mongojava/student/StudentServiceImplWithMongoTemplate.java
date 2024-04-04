package com.example.mongojava.student;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("studentServiceMongoTemplate")
@RequiredArgsConstructor
public class StudentServiceImplWithMongoTemplate implements StudentService {
    private final StudentRepo studentRepository;

    @Override
    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    @Override
    public Page<Student> findAllByPageable(int page, int size) {
        return studentRepository.findAllByPageable(page, size);
    }

    @Override
    public List<Student> findAllByGender(Gender gender) {
        return studentRepository.findAllByGender(gender);
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
        return studentRepository.sortByField(field, direction);
    }

    @Override
    public Student save(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public boolean edit(Student student) {
        return studentRepository.edit(student);
    }

    @Override
    public boolean delete(Student student) {
        return studentRepository.delete(student.getId());
    }
}
