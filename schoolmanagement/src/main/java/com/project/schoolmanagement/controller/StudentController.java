package com.project.schoolmanagement.controller;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    @Autowired
    private IAbsenceService absenceService;

    @GetMapping("/home")
    public String showStudentHome(Model model, HttpSession session) {
        // model.addAttribute("content", "student/student-home");
        model.addAttribute("pageTitle", "Student Home Page");
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

                // model.addAttribute("content", "student/student-absence-list");
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
        absence.setStartDate(new Date(System.currentTimeMillis()));
        absence.setEndDate(new Date(System.currentTimeMillis()));

        model.addAttribute("absence", absence);
        model.addAttribute("student_id", student_id);
        // model.addAttribute("content", "student/student-absence-form");
        return "/student/student-absence-form";
    }

    // Handle the information submitted by Student about absences.
    @PostMapping("/absences/{student_id}/add")
    public String handleSubmitFormAbsenceForAdd(
            @PathVariable("student_id") Long student_id,
            @ModelAttribute("absence") Absence absence,
            Model model) {

        if (absence.getStartDate().compareTo(absence.getEndDate()) > 0) {
            model.addAttribute("message_error", "Ngày bắt đầu phải nhỏ hơn hoặc bằng ngày kết thúc");
            model.addAttribute("absence", absence);
            model.addAttribute("student_id", student_id);
            // model.addAttribute("content", "student/student-absence-form");
            return "/student/student-absence-form";
        }

        absence.setStudent(studentService.getReferenceById(student_id));
        absenceService.save(absence);

        return "redirect:/student/absences/list";
    }

    // Display the list of grades for the student. BỎ
    @GetMapping("/grades/{student_id}")
    public String showListGrades(Model model, @PathVariable("student_id") Long student_id) {
        Student student = studentService.getReferenceById(student_id);
        List<Grade> grades = student.getGrades();

        model.addAttribute("averages", studentService.calculateAverageOfGroupGradesBySubject(grades));
        model.addAttribute("grades", studentService.groupGradesBySubject(grades));
        model.addAttribute("student_id", student_id);

        return "/student/student-grade-list";
    }

    // Display the list of grades for the student.
    @GetMapping("/grades/list")
    public String showListGradesOfStudent(Model model, HttpSession session, RedirectAttributes ra) {
        try {
            Student student = session.getAttribute("student") != null ? (Student) session.getAttribute("student")
                    : null;
            if (student == null) {
                System.out.println("get student from session failed");
                return "redirect:/student/home";
            } else {
                // List<Grade> grades = student.getGrades();
                List<Grade> grades = studentService.getAllGradesOfStudent(student.getStudent_id());
                for (Grade grade : grades) {
                    System.out.println(grade.getGrade_id() + " " + grade.getSubject().getName());
                }
                // model.addAttribute("number", 8.123123);
                model.addAttribute("averages", studentService.calculateAverageOfGroupGradesBySubject2(grades));
                model.addAttribute("grades", studentService.groupGradesBySubject(grades));
                model.addAttribute("student_id", student.getStudent_id());
            }
            System.out.println("showListGradesOfStudent  SUCCESS");
            return "/student/student-grade-list";
        } catch (Exception e) {
            System.out.println("Error in showListGradesOfStudent()");
            ra.addFlashAttribute("message_error", "get Error in showListGradesOfStudent()");
            return "redirect:/student/home";
        }
    }

    @GetMapping("/grades/revision/{id}")
    public String showFormRevisionGrade(Model model, @PathVariable("id") Long id) {
        Grade grade = studentService.getGradeById(id);
        model.addAttribute("grade", grade);
        // model.addAttribute("content", "student/student-grade-revision-form");
        return "/student/student-grade-revision-form";
    }

    @PostMapping("/grades/revision/{id}")
    public String handleSubmitFormRevisionGrade(@PathVariable("id") Long id, @ModelAttribute("grade") Grade grade,
            Model model) {
        Grade grade_db = studentService.getGradeById(id);
        grade_db.setRevision(grade.getRevision());
        studentService.saveGrade(grade_db);
        return "redirect:/student/grades/list";
    }

    // Display the form of editing the profile of the student.
    @GetMapping("/profile")
    public String showFormEditProfile(Model model, HttpSession session) {
        try {
            Student student = session.getAttribute("student") != null ? (Student) session.getAttribute("student")
                    : null;
            if (student == null) {
                System.out.println("get student from session failed");
                return "redirect:/student/home";
            } else {
                model.addAttribute("student", student);
                // model.addAttribute("content", "student/student-profile-form");
            }
            System.out.println("showFormEditProfile  SUCCESS");
            return "/student/student-profile-form";
        } catch (Exception e) {
            System.out.println("Error in showFormEditProfile()");
            return "redirect:/student/home";
        }
    }

    // Handle the information submitted by Student about profile.
    @PostMapping("/profile")
    public String handleProfileSubmitForm(Model model,
            HttpSession session,
            @ModelAttribute("student") Student updatedStudent,
            RedirectAttributes ra) {
        try {
            System.out.println("----------------id: " + updatedStudent.getStudent_id());
            System.out.println("----------------name: " + updatedStudent.getName());
            studentService.save(updatedStudent);
            System.out.println("save st  SUCCESS");

            session.setAttribute("student", updatedStudent);

            System.out.println("handleProfileSubmitForm  SUCCESS");
            ra.addFlashAttribute("message", "Cập nhật thông tin thành công");
            return "redirect:/student/home";

        } catch (Exception e) {
            System.out.println("Error in handleProfileSubmitForm()");
            return "redirect:/student/home";
        }
    }
}
