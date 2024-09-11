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
        addStudent(
                Student.builder()
                        .firstName("John")
                        .lastName("Doe")
                        .gender(Student.Gender.MALE)
                        .age(30)
                        .email("jdoe@mail.com")
                        .build()
        );
        addStudent(
                Student.builder()
                        .firstName("Jane")
                        .lastName("Doe")
                        .gender(Student.Gender.FEMALE)
                        .age(25)
                        .email("jane@mail.com")
                        .build()
        );
        addStudent(
                Student.builder()
                        .firstName("Tibo")
                        .lastName("")
                        .gender(Student.Gender.MALE)
                        .age(28)
                        .email("tibo@mail.com")
                        .photo("/images/student/cat.jpg")
                        .build()
        );
    }

    public void addStudent(Student student) {
        if (student.getId() == null) student.setId(UUID.randomUUID());
        if (student.getPhoto() == null || student.getPhoto().isBlank())
            student.setPhoto(String.format("/images/student/default/%s-default.png", student.getGender().name().toLowerCase().charAt(0)));
        students.add(student);
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
                .filter(student -> student.getFirstName().toLowerCase().contains(name.toLowerCase()) || student.getLastName().toLowerCase().contains(name.toLowerCase()))
                .toList();
    }

    public void deleteStudent(UUID id) {
        students.remove(getStudentById(id));
    }

    public void updateStudent(Student updatedStudent) {
        Student student = getStudentById(updatedStudent.getId());
        if (student == null) return;

        student.setFirstName(updatedStudent.getFirstName());
        student.setLastName(updatedStudent.getLastName());
        student.setGender(updatedStudent.getGender());
        student.setAge(updatedStudent.getAge());
        student.setEmail(updatedStudent.getEmail());
        if (updatedStudent.getPhoto() == null || updatedStudent.getPhoto().isBlank())
            updatedStudent.setPhoto(String.format("/images/student/default/%s-default.png", updatedStudent.getGender().name().toLowerCase().charAt(0)));
        else
            student.setPhoto(updatedStudent.getPhoto());
    }
}
