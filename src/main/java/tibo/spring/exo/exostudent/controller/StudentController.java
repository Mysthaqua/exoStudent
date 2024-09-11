package tibo.spring.exo.exostudent.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
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
        model.addAttribute("action", "Add");
        return "student/formStudent";
    }

    @RequestMapping(value = "/add-student", method = RequestMethod.POST)
    public String addStudentPOST(@Valid @ModelAttribute("student") Student student,
                                 BindingResult result,
                                 Model model,
                                 @RequestParam(name = "image", required = false) MultipartFile photo) {
        if (result.hasErrors()) {
            model.addAttribute("action", "Add");
            return "student/formStudent";
        }

        //        String filename = String.format("%s_%s", UUID.randomUUID(), photo.getOriginalFilename());

        student.setPhoto(""); // TODO: upload photo
        studentService.addStudent(student);
        return String.format("redirect:/students/%s", student.getId());
    }

    @RequestMapping("/search-student")
    public String search(Model model, @RequestParam("studentName") String studentName) {
        model.addAttribute("students", studentService.getStudentsByName(studentName));
        return "student/listStudents";
    }

    @RequestMapping("/delete-student/{id}")
    public String deleteStudent(@PathVariable("id") UUID id) {
        studentService.deleteStudent(id);
        return "redirect:/students";
    }

    @RequestMapping("/update-student/{id}")
    public String updateStudent(@PathVariable("id") UUID id, Model model) {
        model.addAttribute("student", studentService.getStudentById(id));
        model.addAttribute("action", "Edit");
        return "student/formStudent";
    }

    @RequestMapping(value = "/update-student", method = RequestMethod.POST)
    public String updateStudentPOST(@Valid @ModelAttribute("student") Student student,
                                    BindingResult result,
                                    Model model) {
        if (result.hasErrors()) {
            model.addAttribute("action", "Edit");
            return "student/formStudent";
        }

        studentService.updateStudent(student);
        return String.format("redirect:/students/%s", student.getId());
    }
}

