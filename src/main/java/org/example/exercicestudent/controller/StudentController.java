package org.example.exercicestudent.controller;

import org.example.exercicestudent.model.Student;
import org.example.exercicestudent.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
public class StudentController {
    private StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public String getStudents(Model model) {

        return "home";
    }

    @GetMapping("/register")
    public String addStudent(Model model) {
        model.addAttribute("student", new Student());
        return "register";
    }

    @PostMapping("/add")
    public String submitStudent(@ModelAttribute Student student) {
        student.setId(UUID.randomUUID());
        studentService.createStudent(student);
        System.out.println("test");
        return "redirect:/list";
    }
    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("students", studentService.getAllStudents());
        return "list";
    }
    @GetMapping("/students/{id}")
    public String details(@PathVariable UUID id, Model model) {
        model.addAttribute("student", studentService.getStudent(id));
        return "details";
    }

    @GetMapping("/result")
    public String result(@RequestParam(value = "firstName", required = false ) String firstName, Model model) {
        model.addAttribute("students", studentService.getStudentByFirstName(firstName == null ? "" : firstName));
        return "result";
    }

    @PostMapping("/students")
    public String edit(Model model, Student student) {
        studentService.createStudent(student);
        model.addAttribute("student", studentService.getStudent(student.getId()));
        return "details";
    }
    @GetMapping("/del/{id}")
    public String delete(@PathVariable UUID id,Model model) {
        studentService.deleteStudent(id);
        return "redirect:/list";
    }


}
