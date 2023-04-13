package com.project.schoolmanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.schoolmanagement.entity.Absence;
import com.project.schoolmanagement.entity.Student;
import com.project.schoolmanagement.repository.StudentRepository;

@Controller
@RequestMapping("/")
public class HomeController {
    @Autowired
    private StudentRepository studentRepository;

    // @Autowired
    // private AbsenceRepository absenceRepository;

    @GetMapping("home")
    public String showFormHome(Model model) {
        model.addAttribute("name", "World");
        return "home";
    }

    @GetMapping("login")
    public String showFormLoginForType(Model model) {
        model.addAttribute("name", "World");
        return "login";
    }

    @PostMapping("login")
    public String handleLoginSubmit(
            @RequestParam String username,
            @RequestParam String password,
            Model model) {
        if (username.equals("admin") && password.equals("admin")) {
            return "redirect:/home";
        } else {
            return "redirect:/login";
        }
    }

    @GetMapping("test")
    public String showFormTest(Model model) {
        Student student = studentRepository.getReferenceById(Long.valueOf(1));
        List<Absence> listabsence = student.getAbsences();
        model.addAttribute("message", "Successfully load list of absence of student!");
        System.out.println("Successfully load list of absence of student!");
        for (Absence absence : listabsence) {
            System.out.println(absence.getAbsence_id());
            System.out.println(absence.getStudent().getName());
        }
        return "home";
    }
}
