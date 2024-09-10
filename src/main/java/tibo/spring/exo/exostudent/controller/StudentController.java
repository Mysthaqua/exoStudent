package tibo.spring.exo.exostudent.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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
        return "student/listStudents";
    }

    @RequestMapping("/students/{id}")
    public String student(@PathVariable("id") UUID id, Model model) {
        model.addAttribute("student", studentService.getStudentById(id));
        return "student/detailsStudent";
    }

    @RequestMapping("/add-student")
    public String addStudent(Model model) {
        model.addAttribute("student", new Student());
        return "student/addStudent";
    }

    @PostMapping("/add-student")
    public String addStudentPOST(@ModelAttribute("student") Student student/*, @RequestParam(name = "photo", required = false) MultipartFile photo*/) {
        student.setPhoto(""); // TODO: upload photo
        studentService.addStudent(student);
        return "redirect:/students";
    }

    @RequestMapping("/search-student")
    public String search(Model model, @RequestParam("studentName") String studentName) {
        model.addAttribute("students", studentService.getStudentsByName(studentName));
        return "student/listStudents";
    }
}

