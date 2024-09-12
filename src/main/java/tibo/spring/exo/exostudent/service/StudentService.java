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

    /*public void addStudent(Student student) {
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
    }*/
}
