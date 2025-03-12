package dev.jonkursani.restapigr2.mappers;


import dev.jonkursani.restapigr2.dtos.employee.EmployeeDto;
import dev.jonkursani.restapigr2.entities.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EmployeeMapper {
    EmployeeDto toDto(Employee employee);
}
