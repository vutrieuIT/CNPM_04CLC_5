package com.project.schoolmanagement.controller;

import com.project.schoolmanagement.entity.Class;
import com.project.schoolmanagement.entity.Teacher;
import com.project.schoolmanagement.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/giao-vien")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;
    @GetMapping()
    public String home(){
        return "teacher/teacher-home";
    }
    @GetMapping("/quan-ly-diem")
    public String score(Model model){
        Teacher teacher = new Teacher();
        teacher.setName("trieu");
        List<Class> dslop = new ArrayList<>();
        dslop = teacherService.getDSLop(1L);
        model.addAttribute("teacher", teacher);
        model.addAttribute("danhSachLop",dslop);
        return "/teacher/score";
    }
}
