package dev.jonkursani.restapigr2.services;

import dev.jonkursani.restapigr2.dtos.department.CreateDepartmentRequest;
import dev.jonkursani.restapigr2.dtos.department.DepartmentDto;
import dev.jonkursani.restapigr2.dtos.department.DepartmentWithEmployeeCount;
import dev.jonkursani.restapigr2.dtos.department.UpdateDepartmentRequest;

import java.util.List;

public interface DepartmentService {
    List<DepartmentDto> findAll();
    DepartmentDto create(CreateDepartmentRequest request);
    DepartmentDto update(Integer id, UpdateDepartmentRequest request);
    DepartmentDto findById(Integer id);
    void delete(Integer id);
    List<DepartmentWithEmployeeCount> findAllWithEmployeeCount();
}