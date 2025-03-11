package dev.jonkursani.restapigr2.dtos.student;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateStudentRequest {
    private String name;
    private String email;
//    private LocalDateTime updatedAt;
//    private long updatedBy;
}
