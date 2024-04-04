package com.example.mongojava.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    private final StudentService studentService;

    @Autowired
    public StudentController(@Qualifier("studentServiceMongoTemplate") StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public HttpEntity<List<Student>> findAll() {
        return ResponseEntity.ok(studentService.findAll());
    }

    @GetMapping("/byPage")
    public HttpEntity<Page<Student>> findAllByPageable(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size
    ) {
        return ResponseEntity.ok(studentService.findAllByPageable(page, size));
    }

    @GetMapping("/byGender")
    public HttpEntity<List<Student>> findAllByGender(
            @RequestParam(name = "gender", defaultValue = "MALE") Gender gender
    ) {
        return ResponseEntity.ok(studentService.findAllByGender(gender));
    }

    @GetMapping("/byGroup")
    public HttpEntity<List<Student>> findAllByGroup(
            @RequestParam(name = "group", defaultValue = "660e2f5928795e166affdb83") String groupId
    ) {
        return ResponseEntity.ok(studentService.findAllByGroupId(groupId));
    }

    @GetMapping("/bySort")
    public HttpEntity<List<Student>> sortingByField(
            @RequestParam(name = "field", defaultValue = "name") String field,
            @RequestParam(name = "sortType", defaultValue = "ASC") Sort.Direction direction
    ) {
        return ResponseEntity.ok(studentService.sortByField(field, direction));
    }

    @GetMapping("/{id}")
    public HttpEntity<Student> findById(@PathVariable("id") String id) {
        Student student = studentService.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));
        return ResponseEntity.ok(student);
    }

    @PostMapping
    public HttpEntity<Student> save(@RequestBody Student student) {
        return ResponseEntity.ok(studentService.save(student));
    }

    @PutMapping("/edit")
    public HttpEntity<Void> edit(@RequestBody Student st) {
        studentService.edit(st);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public HttpEntity<Void> delete(@PathVariable("id") String id) {
        Student student = studentService.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));
        studentService.delete(student);
        return ResponseEntity.noContent().build();
    }
}
