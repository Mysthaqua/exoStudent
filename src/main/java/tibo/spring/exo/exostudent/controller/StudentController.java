package tibo.spring.exo.exostudent.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tibo.spring.exo.exostudent.entity.Student;
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
        model.addAttribute("students", studentService.findAll());
        return "student/listStudents";
    }

    @RequestMapping("/students/{id}")
    public String student(@PathVariable("id") UUID id, Model model) {
        model.addAttribute("student", studentService.findById(id));
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

        /* ---------- */
        // TODO: upload photo
        // String filename = String.format("%s_%s", UUID.randomUUID(), photo.getOriginalFilename());
        if (student.getPhoto() == null || student.getPhoto().isBlank())
            student.setPhoto(String.format("/images/student/default/%s-default.png", student.getGender().name().toLowerCase().charAt(0)));
        /* ---------- */

        studentService.save(student);
        return String.format("redirect:/students/%s", student.getId());
    }

    @RequestMapping("/search-student")
    public String search(Model model, @RequestParam("studentName") String studentName) {
        model.addAttribute("students", studentService.findByName(studentName));
        return "student/listStudents";
    }

    @RequestMapping("/delete-student/{id}")
    public String deleteStudent(@PathVariable("id") UUID id) {
        studentService.delete(id);
        return "redirect:/students";
    }

    @RequestMapping("/update-student/{id}")
    public String updateStudent(@PathVariable("id") UUID id, Model model) {
        model.addAttribute("student", studentService.findById(id));
        model.addAttribute("action", "Edit");
        return "student/formStudent";
    }

    @RequestMapping(value = "/update-student", method = RequestMethod.POST)
    public String updateStudentPOST(@Valid @ModelAttribute("student") Student student,
                                    BindingResult result,
                                    Model model,
                                    @RequestParam(name = "image", required = false) MultipartFile photo) {
        if (result.hasErrors()) {
            model.addAttribute("action", "Edit");
            return "student/formStudent";
        }

        /* ---------- */
        // TODO: upload photo
        // String filename = String.format("%s_%s", UUID.randomUUID(), photo.getOriginalFilename());
        if (student.getPhoto() == null || student.getPhoto().isBlank())
            student.setPhoto(String.format("/images/student/default/%s-default.png", student.getGender().name().toLowerCase().charAt(0)));
        /* ---------- */

        studentService.save(student);
        return String.format("redirect:/students/%s", student.getId());
    }
}

