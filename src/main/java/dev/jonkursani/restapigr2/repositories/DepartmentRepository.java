package dev.jonkursani.restapigr2.repositories;

import dev.jonkursani.restapigr2.entities.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {
    @Query("SELECT d FROM Department d LEFT JOIN FETCH d.employees")
//    @Query("SELECT d, COUNT(e.id) FROM Department d LEFT JOIN Employee e ON d.id = e.department.id GROUP BY d.id")
    List<Department> findAllWithEmployeeCount();
}