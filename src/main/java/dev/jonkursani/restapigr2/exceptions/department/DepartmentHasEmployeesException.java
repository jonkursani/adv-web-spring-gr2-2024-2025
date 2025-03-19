package dev.jonkursani.restapigr2.exceptions.department;

public class DepartmentHasEmployeesException extends IllegalStateException {
    public DepartmentHasEmployeesException(Integer departmentId) {
        super("Department with id " + departmentId + " has employees.");
    }
}
