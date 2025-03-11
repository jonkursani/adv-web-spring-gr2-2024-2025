package dev.jonkursani.restapigr2.controllers;

import dev.jonkursani.restapigr2.dtos.department.CreateDepartmentRequest;
import dev.jonkursani.restapigr2.dtos.department.DepartmentDto;
import dev.jonkursani.restapigr2.dtos.department.UpdateDepartmentRequest;
import dev.jonkursani.restapigr2.services.DepartmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/departments")
@RequiredArgsConstructor
public class DepartmentController {
    private final DepartmentService service;

    @GetMapping
    public ResponseEntity<List<DepartmentDto>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @PostMapping
    @Validated
    public ResponseEntity<DepartmentDto> create(@Valid @RequestBody CreateDepartmentRequest request) {
        var createdDepartment = service.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdDepartment);
//        return new ResponseEntity<>(createdDepartment, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Validated
    public ResponseEntity<Void> update(@PathVariable Integer id, @Valid @RequestBody UpdateDepartmentRequest request) {
        service.update(id, request);
        return ResponseEntity.noContent().build();
    }
}
