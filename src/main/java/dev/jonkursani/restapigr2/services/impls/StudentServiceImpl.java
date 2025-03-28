package dev.jonkursani.restapigr2.services.impls;

import dev.jonkursani.restapigr2.dtos.student.CreateStudentRequest;
import dev.jonkursani.restapigr2.dtos.student.StudentDto;
import dev.jonkursani.restapigr2.dtos.student.UpdateStudentRequest;
import dev.jonkursani.restapigr2.entities.Student;
import dev.jonkursani.restapigr2.repositories.StudentRepository;
import dev.jonkursani.restapigr2.services.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
    private final StudentRepository repository;

    @Override
    public List<StudentDto> findAll() {
        return repository.findAll()
                .stream()
                .map(student -> new StudentDto(student.getId(), student.getName(), student.getEmail()))
                .toList();
    }

    @Override
    public StudentDto findById(Long id) {
        return repository.findById(id)
                .map(student -> new StudentDto(student.getId(), student.getName(), student.getEmail()))
                .orElseThrow(() -> new RuntimeException("Student with id " + id + " not found"));
    }

    @Override
    public StudentDto create(CreateStudentRequest request) {
        var userToCreate = new Student(0, request.getName(), request.getEmail(), true, LocalDateTime.now(),1);
        var createdUser = repository.save(userToCreate);
        return new StudentDto(createdUser.getId(), createdUser.getName(), createdUser.getEmail());
    }

    @Override
    public void update(long id, UpdateStudentRequest request) {
        var studentFromDb = findById(id);
        if (studentFromDb != null) {
            var studentToUpdate = new Student(id, request.getName(), request.getEmail(), true, LocalDateTime.now(), 1);
            repository.save(studentToUpdate);
        }
    }

    @Override
    public void delete(Long id) {
        var studentFromDb = findById(id);
        if (studentFromDb != null) {
            repository.deleteById(id);
        }
    }
}
