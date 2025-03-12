package dev.jonkursani.restapigr2.services.impls;

import dev.jonkursani.restapigr2.dtos.employee.CreateEmployeeRequest;
import dev.jonkursani.restapigr2.dtos.employee.EmployeeDto;
import dev.jonkursani.restapigr2.mappers.DepartmentMapper;
import dev.jonkursani.restapigr2.mappers.EmployeeMapper;
import dev.jonkursani.restapigr2.repositories.EmployeeRepository;
import dev.jonkursani.restapigr2.services.DepartmentService;
import dev.jonkursani.restapigr2.services.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository repository;
    private final EmployeeMapper employeeMapper;
    private final DepartmentService departmentService;
    private final DepartmentMapper departmentMapper;

    @Override
    public List<EmployeeDto> findAll(Integer departmentId) {
        if (departmentId != null) {
            var departmentDto = departmentService.findById(departmentId);
            var department = departmentMapper.toEntity(departmentDto);
            return repository.findAllByDepartment(department)
                    .stream()
                    .map(employeeMapper::toDto)
                    .toList();
        } else {
            return repository.findAll()
                    .stream()
                    .map(employeeMapper::toDto)
                    .toList();
        }
    }

    @Override
    public EmployeeDto create(CreateEmployeeRequest request) {
        return null;
    }
}
