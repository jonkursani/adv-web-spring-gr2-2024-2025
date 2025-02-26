package dev.jonkursani.restapigr2.controllers;

import dev.jonkursani.restapigr2.dtos.CreateStudentRequest;
import dev.jonkursani.restapigr2.dtos.StudentDto;
import dev.jonkursani.restapigr2.dtos.UpdateStudentRequest;
import dev.jonkursani.restapigr2.services.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/students")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService service;

    @GetMapping
    public ResponseEntity<List<StudentDto>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) // 201
    public ResponseEntity<StudentDto> create(@RequestBody CreateStudentRequest request) {
        return ResponseEntity.ok(service.create(request));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT) // 204
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody UpdateStudentRequest request) {
        service.update(id, request);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT) // 204
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }
}
