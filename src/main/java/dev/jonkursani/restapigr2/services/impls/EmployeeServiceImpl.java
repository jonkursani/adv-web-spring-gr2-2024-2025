package dev.jonkursani.restapigr2.services.impls;

import dev.jonkursani.restapigr2.dtos.employee.CreateEmployeeRequest;
import dev.jonkursani.restapigr2.dtos.employee.EmployeeDto;
import dev.jonkursani.restapigr2.dtos.employee.UpdateEmployeeRequest;
import dev.jonkursani.restapigr2.entities.Employee;
import dev.jonkursani.restapigr2.exceptions.employee.EmailAlreadyExistsException;
import dev.jonkursani.restapigr2.exceptions.employee.EmployeeNotFoundException;
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
    public EmployeeDto findById(Integer id) {
        return repository.findById(id)
                .map(employeeMapper::toDto)
                .orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    @Override
    public EmployeeDto create(CreateEmployeeRequest request) {
         if(repository.existsEmployeeByEmail(request.getEmail())) {
//             throw new IllegalArgumentException("Employee with email " + request.getEmail() + " already exists.");
             throw new EmailAlreadyExistsException(request.getEmail());
         }

         var employee = new Employee();
         employee.setFirstName(request.getFirstName());
         employee.setLastName(request.getLastName());

//         var departmentDto = departmentService.findById(request.getDepartmentId());
//         var department = departmentMapper.toEntity(departmentDto);
        var department = departmentMapper.toEntity(departmentService.findById(request.getDepartmentId()));
        employee.setDepartment(department);

        employee.setHireDate(request.getHireDate());
        employee.setEmail(request.getEmail());

        var createdEmployee = repository.save(employee);
        return employeeMapper.toDto(createdEmployee);
    }

    @Override
    public EmployeeDto update(Integer id, UpdateEmployeeRequest request) {
        var employeeFromDb = repository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));


//        if (!employeeFromDb.getEmail().equals(request.getEmail())) {
//            if (repository.existsEmployeeByEmail(request.getEmail())) {
//                throw new EmailAlreadyExistsException(request.getEmail());
//            }
//        }

        if (!employeeFromDb.getEmail().equals(request.getEmail()) &&
                repository.existsEmployeeByEmail(request.getEmail())) {
            throw new EmailAlreadyExistsException(request.getEmail());
        }

        employeeFromDb.setFirstName(request.getFirstName());
        employeeFromDb.setLastName(request.getLastName());
        var department = departmentMapper.toEntity(departmentService.findById(request.getDepartmentId()));
        employeeFromDb.setDepartment(department);
        employeeFromDb.setHireDate(request.getHireDate());
        employeeFromDb.setEmail(request.getEmail());

        var updatedEmployee = repository.save(employeeFromDb);

        return employeeMapper.toDto(updatedEmployee);
    }

    @Override
    public void delete(Integer id) {
        repository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));

        repository.deleteById(id);
    }
}