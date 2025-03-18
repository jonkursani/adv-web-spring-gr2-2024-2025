package dev.jonkursani.restapigr2.dtos.employee;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateEmployeeRequest {
    @NotBlank(message = "First name is required")
    @Size(max = 50, message = "First name must be less than or equal to {max} characters")
    private String firstName;

    @NotBlank(message = "Last name is required")
    @Size(max = 50, message = "Last name must be less than or equal to {max} characters")
    private String lastName;

    private Integer departmentId;

    private LocalDate hireDate;

    @Email(message = "Email is not valid")
    @Size(max = 100, message = "Email must be less than or equal to {max} characters")
    private String email;
}