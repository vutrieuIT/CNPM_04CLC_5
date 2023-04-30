package com.project.schoolmanagement.controller;

import java.util.List;

import com.project.schoolmanagement.entity.Teacher;
import com.project.schoolmanagement.repository.TeacherRepository;
import com.project.schoolmanagement.service.TeacherService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.project.schoolmanagement.entity.Absence;
import com.project.schoolmanagement.entity.Student;
import com.project.schoolmanagement.repository.StudentRepository;
import com.project.schoolmanagement.service.AccountService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/")
public class HomeController {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private AccountService accountService;

    @Autowired
    private TeacherService teacherService;

    @GetMapping("/")
    public String showBase() {
        return "home";
    }
    // @Autowired
    // private AbsenceRepository absenceRepository;

    @GetMapping("login")
    public String showFormLoginForType() {
        return "login";
    }

    @PostMapping("login")
    public String handleLoginSubmit(
            @RequestParam String username,
            @RequestParam String password,
            @RequestParam("userType") String userType,
            Model model,
            RedirectAttributes ra,
            HttpSession session) {

        if (userType.equals("student")) {
            if (accountService.studentLogin(username, password)) {
                return "redirect:/student/home/1";
            } else {
                // ra.addFlashAttribute("param.message", "Tài khoản không tồn tại!");

                ra.addFlashAttribute("message", "Tài khoản không tồn tại!");
                return "redirect:/#login";
            }
        } else if (userType.equals("teacher")) {
            if (accountService.teacherLogin(username, password)) {
                // TODO: redirect to teacher home page
                Teacher teacher = teacherService.login(username,password);
                ra.addFlashAttribute("teacher", teacher);
                session.setAttribute("teacher", teacher);
                return "redirect:/giao-vien";
            } else {
                model.addAttribute("message_error", "Tài khoản không tồn tại!");
                return "login";
            }
        } else if (userType.equals("admin")) {
            if (accountService.adminLogin(username, password)) {
                return "home";
            } else {
                return "login";
            }
        } else {
            model.addAttribute("message_error", "Lỗi");
            return "redirect:/login";
        }

        // if (username.equals("admin") && password.equals("admin")) {
        // return "redirect:/home";
        // } else {
        // return "redirect:/login";
        // }
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
