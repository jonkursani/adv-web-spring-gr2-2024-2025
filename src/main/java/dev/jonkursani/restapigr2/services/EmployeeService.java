package dev.jonkursani.restapigr2.services;

import dev.jonkursani.restapigr2.dtos.employee.CreateEmployeeRequest;
import dev.jonkursani.restapigr2.dtos.employee.EmployeeDto;

import java.util.List;

public interface EmployeeService {
    List<EmployeeDto> findAll(Integer departmentId);
    EmployeeDto create(CreateEmployeeRequest request);
}