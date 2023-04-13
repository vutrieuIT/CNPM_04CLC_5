package com.demo.schoolmanagementapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.demo.schoolmanagementapp.entity.Absence;
import com.demo.schoolmanagementapp.entity.Student;
import com.demo.schoolmanagementapp.repository.AbsenceRepository;
import com.demo.schoolmanagementapp.service.StudentService;

@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    // !!!
    @Autowired
    private AbsenceRepository absenceRepository;

    @GetMapping("/home")
    public String showStudentHome() {
        return "student/student-home";
    }

    // Return the list of days the student was absent.
    @GetMapping("/absences/{student_id}")
    public String showListAbsences(Model model, @PathVariable("student_id") Long student_id) {

        Student student = studentService.getReferenceById(student_id);
        List<Absence> list_absence = student.getAbsences();
        model.addAttribute("absences", list_absence);
        model.addAttribute("student_id", student_id);

        return "/student/student-absence-list";
    }

    // Display the form for entering additional information about absences.
    @GetMapping("/absences/{student_id}/add")
    public String showFormAbsenceForAdd(Model model, @PathVariable("student_id") Long student_id) {
        Absence absence = new Absence();
        model.addAttribute("absence", absence);
        model.addAttribute("student_id", student_id);

        return "/student/student-absence-form";
    }

    // Handle the information submitted by Student about absences.
    @PostMapping("/absences/{student_id}/add")
    public String handleSubmitFormAbsenceForAdd(
            @PathVariable("student_id") Long student_id,
            @ModelAttribute("absence") Absence absence,
            Model model) {

        absence.setStudent(studentService.getReferenceById(student_id));
        absenceRepository.save(absence);

        return "redirect:/student/absences/" + student_id;
    }

    // Display the list of grades for the student.
    @GetMapping("/grades/{student_id}")
    public String showListGrades(Model model, @PathVariable("student_id") Long student_id) {

        Student student = studentService.getReferenceById(student_id);
        model.addAttribute("grades", student.getGrades());
        model.addAttribute("student_id", student_id);

        return "/student/student-grade-list";
    }
    
}
