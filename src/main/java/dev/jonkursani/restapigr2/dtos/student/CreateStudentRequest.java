package dev.jonkursani.restapigr2.dtos.student;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateStudentRequest {
    private String name;
    private String email;
//    private LocalDateTime createdAt;
//    private long CreatedBy;
}
