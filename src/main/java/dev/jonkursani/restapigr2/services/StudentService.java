package dev.jonkursani.restapigr2.services;

import dev.jonkursani.restapigr2.dtos.student.CreateStudentRequest;
import dev.jonkursani.restapigr2.dtos.student.StudentDto;
import dev.jonkursani.restapigr2.dtos.student.UpdateStudentRequest;

import java.util.List;

public interface StudentService {
    List<StudentDto> findAll();
    StudentDto findById(Long id);
    StudentDto create(CreateStudentRequest request);
    void update(long id, UpdateStudentRequest request);
    void delete(Long id);
}
