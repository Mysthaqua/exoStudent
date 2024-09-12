package tibo.spring.exo.exostudent.service;

import org.springframework.stereotype.Service;
import tibo.spring.exo.exostudent.dao.StudentRepository;
import tibo.spring.exo.exostudent.entity.Student;

import java.util.List;
import java.util.UUID;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;

        // Populate the database with some sample data
        studentRepository.save(Student.builder()
                .firstName("John")
                .lastName("Doe")
                .gender(Student.Gender.MALE)
                .age(30)
                .email("jdoe@mail.com")
                .build());
        studentRepository.save(Student.builder()
                .firstName("Jane")
                .lastName("Doe")
                .gender(Student.Gender.FEMALE)
                .age(25)
                .email("jane@mail.com")
                .build()
        );
        studentRepository.save(Student.builder()
                .firstName("Tibo")
                .lastName("Myster")
                .gender(Student.Gender.MALE)
                .age(28)
                .email("tibo@mail.com")
                .photo("/images/student/cat.jpg")
                .build()
        );
    }

    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    public Student findById(UUID id) {
        return studentRepository.findById(id).orElse(null);
    }

    public List<Student> findByName(String name) {
        return studentRepository.findByName(name);
    }

    public void save(Student student) {
        studentRepository.save(student);
    }

    public void delete(UUID id) {
        studentRepository.deleteById(id);
    }
}
