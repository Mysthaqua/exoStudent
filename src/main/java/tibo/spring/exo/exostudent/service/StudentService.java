package tibo.spring.exo.exostudent.service;

import org.springframework.stereotype.Service;
import tibo.spring.exo.exostudent.model.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class StudentService {
    private final List<Student> students;

    public StudentService() {
        students = new ArrayList<>();
    }

    public Student createStudent(String firstName, String lastName, int age, String email) {
        Student student = Student.builder()
                .id(UUID.randomUUID())
                .firstName(firstName)
                .lastName(lastName)
                .age(age)
                .email(email)
                .build();
        students.add(student);
        return student;
    }

    public List<Student> getAllStudents() {
        return students;
    }

    public Student getStudentById(UUID id) {
        return students.stream().filter(student -> student.getId().equals(id)).findFirst().orElse(null);
    }

    public List<Student> getStudentsByName(String name) {
        return students
                .stream()
                .filter(student -> student.getFirstName().equals(name) || student.getLastName().equals(name))
                .toList();
    }
}
