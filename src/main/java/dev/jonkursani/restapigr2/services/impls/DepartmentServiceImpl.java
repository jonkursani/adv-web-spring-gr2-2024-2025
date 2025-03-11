package dev.jonkursani.restapigr2.services.impls;

import dev.jonkursani.restapigr2.dtos.department.CreateDepartmentRequest;
import dev.jonkursani.restapigr2.dtos.department.DepartmentDto;
import dev.jonkursani.restapigr2.dtos.department.UpdateDepartmentRequest;
import dev.jonkursani.restapigr2.exceptions.department.DepartmentNotFoundException;
import dev.jonkursani.restapigr2.mappers.DepartmentMapper;
import dev.jonkursani.restapigr2.repositories.DepartmentRepository;
import dev.jonkursani.restapigr2.services.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository repository;
    private final DepartmentMapper mapper;

    @Override
    public List<DepartmentDto> findAll() {
        return repository.findAll()
                .stream()
                .map(department ->
                        new DepartmentDto(department.getId(), department.getName(), department.getLocation()))
                .toList();
    }

    @Override
    public DepartmentDto create(CreateDepartmentRequest request) {
        var department = mapper.toEntity(request);
        var createdDepartment = repository.save(department);
        return mapper.toDto(createdDepartment);
    }

    @Override
    public void update(Integer id, UpdateDepartmentRequest request) {
        var departmentFromDb = repository.findById(id)
                .orElseThrow(() -> new DepartmentNotFoundException(id));
        mapper.updateEntityFromDto(request, departmentFromDb);
        repository.save(departmentFromDb);
    }
}
