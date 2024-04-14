package com.example.springbootadvanced.student;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    private final StudentService studentService;

    @Autowired
    public StudentController(@Qualifier("StudentServiceImplWithCaffeine") StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public HttpEntity<List<Student>> findAll() {
        return ResponseEntity.ok(studentService.findAll());
    }

    @GetMapping("/{id}")
    public HttpEntity<Student> findOne(@PathVariable("id") long id) {
        return ResponseEntity.ok(studentService.findOne(id));
    }

    @PostMapping
    public HttpEntity<Student> save(@RequestBody StudentCreateDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(studentService.save(dto));
    }

    @PutMapping("/{id}")
    public HttpEntity<Student> edit(@PathVariable("id") long id, @RequestBody StudentCreateDto dto) {
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(studentService.edit(dto, id));
    }

    @DeleteMapping("/{id}")
    public HttpEntity<Boolean> delete(@PathVariable("id") long id) {
        return ResponseEntity.ok(studentService.delete(id));
    }

    @ExceptionHandler({NotFoundException.class})
    public HttpEntity<ErrorDto> handle(NotFoundException e, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ErrorDto.builder()
                        .code(404)
                        .path(request.getRequestURI())
                        .timestamp(LocalDateTime.now())
                        .error(e.getMessage())
                        .build());
    }
}
