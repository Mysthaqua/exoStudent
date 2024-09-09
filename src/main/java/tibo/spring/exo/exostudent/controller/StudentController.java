package tibo.spring.exo.exostudent.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import tibo.spring.exo.exostudent.model.Student;
import tibo.spring.exo.exostudent.service.StudentService;

import java.util.UUID;

@Controller
public class StudentController {
    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @RequestMapping("/students")
    public String students(Model model) {
        model.addAttribute("students", studentService.getAllStudents());
        return "students";
    }

    @RequestMapping(value = "/students", method = RequestMethod.POST)
    public String studentsPOST(Model model, @RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName, @RequestParam("age") int age, @RequestParam("email") String email) {
        Student student = studentService.createStudent(firstName, lastName, age, email);
        return student(student.getId(), model);
    }

    @RequestMapping("/students/{id}")
    public String student(@PathVariable("id") UUID id, Model model) {
        model.addAttribute("student", studentService.getStudentById(id));
        return "student";
    }

    @RequestMapping("/signup")
    public String signup() {
        return "signup";
    }

    @RequestMapping("/search")
    public String search(Model model, @RequestParam("studentName") String studentName) {
        model.addAttribute("students", studentService.getStudentsByName(studentName));
        return "search";
    }
}
