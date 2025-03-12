package dev.jonkursani.restapigr2.dtos.employee;

import dev.jonkursani.restapigr2.dtos.department.DepartmentDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto {
    private Integer id;
    private String firstName;
    private String lastName;
    private DepartmentDto department;
    private LocalDate hireDate;
    private String email;
}