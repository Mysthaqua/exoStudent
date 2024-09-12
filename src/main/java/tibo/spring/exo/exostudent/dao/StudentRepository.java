package tibo.spring.exo.exostudent.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tibo.spring.exo.exostudent.entity.Student;

import java.util.List;
import java.util.UUID;

public interface StudentRepository extends JpaRepository<Student, UUID> {
    @Query("SELECT s FROM Student s WHERE s.firstName ILIKE %:name% OR s.lastName ILIKE %:name%")
    List<Student> findByName(String name);
}
