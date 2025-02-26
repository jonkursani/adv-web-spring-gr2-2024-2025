package dev.jonkursani.restapigr2.repositories;

import dev.jonkursani.restapigr2.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
}
