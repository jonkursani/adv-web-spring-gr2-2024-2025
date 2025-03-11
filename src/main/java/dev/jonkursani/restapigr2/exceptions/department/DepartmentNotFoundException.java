package dev.jonkursani.restapigr2.exceptions.department;

import dev.jonkursani.restapigr2.exceptions.ResourceNotFoundException;

public class DepartmentNotFoundException extends ResourceNotFoundException {
    public DepartmentNotFoundException(Integer id) {
        super("Department with id " + id + " not found");
    }
}
