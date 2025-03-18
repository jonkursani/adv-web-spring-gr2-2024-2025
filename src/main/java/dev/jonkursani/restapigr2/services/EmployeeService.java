package dev.jonkursani.restapigr2.services;

import dev.jonkursani.restapigr2.dtos.employee.CreateEmployeeRequest;
import dev.jonkursani.restapigr2.dtos.employee.EmployeeDto;
import dev.jonkursani.restapigr2.dtos.employee.UpdateEmployeeRequest;

import java.util.List;

public interface EmployeeService {
    List<EmployeeDto> findAll(Integer departmentId);
    EmployeeDto findById(Integer id);
    EmployeeDto create(CreateEmployeeRequest request);
    EmployeeDto update(Integer id, UpdateEmployeeRequest request);
    void delete(Integer id);
}