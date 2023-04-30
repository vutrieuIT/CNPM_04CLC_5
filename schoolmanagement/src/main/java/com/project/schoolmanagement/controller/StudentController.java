package com.project.schoolmanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.schoolmanagement.entity.Absence;
import com.project.schoolmanagement.entity.Grade;
import com.project.schoolmanagement.entity.Student;
import com.project.schoolmanagement.service.IAbsenceService;
import com.project.schoolmanagement.service.IStudentService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private IStudentService studentService;
    // !!!
    @Autowired
    private IAbsenceService absenceService;

    @GetMapping("/home")
    public String showStudentHome(Model model, HttpSession session) {
        model.addAttribute("content", "student/student-home");
        model.addAttribute("pageTitle", "Student Home");
        // Student student = (Student) session.getAttribute("student");
        // System.out.println(student);
        return "student/student-home";
    }

    // Return the list of days the student was absent.
    @GetMapping("/absences/list")
    public String showListAbsences(Model model, HttpSession session) {
        try {
            Student student = (Student) session.getAttribute("student");
            try {
                List<Absence> list_absence = absenceService.getAllAbsencesOfStudent(student);
                model.addAttribute("absences", list_absence);
                model.addAttribute("student_id", student.getStudent_id());

                model.addAttribute("content", "student/student-absence-list");
                System.out.println("showListAbsences  SUCCESS");
                return "student/student-absence-list";
            } catch (Exception e) {
                System.out.println("Error in getAbsences()");
                return "redirect:/student/home";
            }
            
        } catch (Exception e) {
            return "redirect:/student/home";
        }

    }

    // Display the form for entering additional information about absences.
    @GetMapping("/absences/{student_id}/add")
    public String showFormAbsenceForAdd(Model model, @PathVariable("student_id") Long student_id) {
        Absence absence = new Absence();
        model.addAttribute("absence", absence);
        model.addAttribute("student_id", student_id);
        model.addAttribute("content", "student/student-absence-form");
        return "/student/student-absence-form";
    }

    // Handle the information submitted by Student about absences.
    @PostMapping("/absences/{student_id}/add")
    public String handleSubmitFormAbsenceForAdd(
            @PathVariable("student_id") Long student_id,
            @ModelAttribute("absence") Absence absence,
            Model model) {

        absence.setStudent(studentService.getReferenceById(student_id));
        absenceService.save(absence);

        return "redirect:/student/absences/list";
    }

    // Display the list of grades for the student.
    @GetMapping("/grades/{student_id}")
    public String showListGrades(Model model, @PathVariable("student_id") Long student_id) {
        Student student = studentService.getReferenceById(student_id);
        List<Grade> grades = student.getGrades();

        model.addAttribute("averages", studentService.calculateAverageOfGroupGradesBySubject(grades));
        model.addAttribute("grades", studentService.groupGradesBySubject(grades));
        model.addAttribute("student_id", student_id);

        return "/student/student-grade-list";
    }

}
