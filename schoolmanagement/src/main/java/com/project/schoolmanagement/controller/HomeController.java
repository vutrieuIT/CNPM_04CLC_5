package com.project.schoolmanagement.controller;

import java.util.List;

import com.project.schoolmanagement.entity.Teacher;
import com.project.schoolmanagement.service.ITeacherService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.schoolmanagement.entity.Absence;
import com.project.schoolmanagement.entity.Student;
import com.project.schoolmanagement.repository.StudentRepository;
import com.project.schoolmanagement.service.IAccountService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/")
public class HomeController {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private IAccountService accountService;

    @Autowired
    private ITeacherService teacherService;

    @GetMapping("/")
    public String showHome(Model model) {
        model.addAttribute("pageTitle", "Home Page");
        // model.addAttribute("pageContent", "Welcome to our website!");
        model.addAttribute("content", "home");
        System.out.println("Get mapping '/'");
        return "home";
    }

    @GetMapping("logout")
    public String logout(RedirectAttributes ra, HttpSession session) {
        session.invalidate();
        ra.addFlashAttribute("message", "Đăng xuất thành công!");
        return "redirect:/";
    }

    @PostMapping("login")
    public String handleLoginSubmit(
            @RequestParam String username,
            @RequestParam String password,
            @RequestParam("userType") String userType,
            Model model,
            RedirectAttributes ra,
            HttpSession session,
            HttpServletRequest request) {
        try {
            if (userType.equals("student")) {
                Student student = accountService.studentLogin(username, password);
                if (student != null) {

                    session = request.getSession();
                    session.setAttribute("student", student);
                    ra.addFlashAttribute("message", "Xin chào " + student.getName() + "!");
                    return "redirect:/student/home";
                } else {
                    ra.addFlashAttribute("message_error", "Tài khoản sinh viên không tồn tại!");
                    return "redirect:/#login";
                }
            }

            if (userType.equals("teacher")) {
                if (accountService.teacherLogin(username, password)) {
                    
                    Teacher teacher = teacherService.login(username, password);
                    ra.addFlashAttribute("teacher", teacher);
                    session.setAttribute("teacher", teacher);
                    return "redirect:/giao-vien";
                } else {
                    model.addAttribute("message_error", "Tài khoản không tồn tại!");
                    return "redirect:/#login";
                }
            }

            if (userType.equals("admin")) {
                if (accountService.adminLogin(username, password)) {
                    return "home";
                } else {
                    return "redirect:/#login";
                }
            }
        } catch (Exception e) {
            ra.addFlashAttribute("message_error", "Lỗi đăng nhập");
            return "redirect:/#login";
        }
        return username;
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
