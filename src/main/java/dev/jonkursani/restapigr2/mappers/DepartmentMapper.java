package dev.jonkursani.restapigr2.mappers;

import dev.jonkursani.restapigr2.dtos.department.CreateDepartmentRequest;
import dev.jonkursani.restapigr2.dtos.department.DepartmentDto;
import dev.jonkursani.restapigr2.dtos.department.DepartmentWithEmployeeCount;
import dev.jonkursani.restapigr2.dtos.department.UpdateDepartmentRequest;
import dev.jonkursani.restapigr2.entities.Department;
import dev.jonkursani.restapigr2.entities.Employee;
import org.mapstruct.*;

import java.util.Set;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DepartmentMapper {
    DepartmentDto toDto(Department department);
    Department toEntity(CreateDepartmentRequest request);
    Department toEntity(DepartmentDto request);
    void updateEntityFromDto(UpdateDepartmentRequest request, @MappingTarget Department department);

    @Mapping(target = "employeeCount", source = "employees", qualifiedByName = "countEmployees")
    DepartmentWithEmployeeCount toDepartmentWithEmployeeCount(Department department);

    @Named("countEmployees")
    default int countEmployees(Set<Employee> employees) {
        if (employees == null) {
            return 0;
        }

        return employees.size();
    }
}