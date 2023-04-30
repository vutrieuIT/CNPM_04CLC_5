package com.project.schoolmanagement.controller;

import com.project.schoolmanagement.dto.ClassSubjectByTeacherIdDTO;
import com.project.schoolmanagement.dto.GradeDTO;
import com.project.schoolmanagement.entity.Class;
import com.project.schoolmanagement.entity.Teacher;
import com.project.schoolmanagement.form.GradeForm;
import com.project.schoolmanagement.repository.GradeRepository;
import com.project.schoolmanagement.service.GradeService;
import com.project.schoolmanagement.service.TeacherService;
import jakarta.servlet.http.HttpSession;
import jogamp.nativewindow.windows.GDI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/giao-vien")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private GradeService gradeService;
    @GetMapping()
    public String home(){

        return "teacher/teacher-home";
    }
    @GetMapping("/quan-ly-diem")
    public String getClassList(Model model, HttpSession session){
        Teacher teacher = (Teacher) session.getAttribute("teacher");
        List<ClassSubjectByTeacherIdDTO> dslop = new ArrayList<>();
        dslop = teacherService.getDanhSachLop(teacher.getTeacher_id());
        model.addAttribute("teacher", teacher);
        model.addAttribute("danhSach",dslop);
        return "/teacher/score";
    }

    @GetMapping("/quan-ly-diem/danh-sach")
    public String getScoreTable(@RequestParam(name = "class_id") Long class_id,
                                @RequestParam(name = "subject_id") Long subject_id,
                                Model model, HttpSession session){
        session.setAttribute("subject_id", subject_id);
        GradeForm gradeForm = new GradeForm();
        List<GradeDTO> scores = gradeService.getScoreTable(class_id, subject_id);
        gradeForm.setGrades(scores);
        if (!model.containsAttribute("gradeForm")) {
            model.addAttribute("gradeForm", new GradeForm());
        }
        model.addAttribute("gradeForm", gradeForm);
        return "teacher/control-score";
    }

//    @PostMapping("/quan-ly-diem/luu")
//    public String save(@ModelAttribute("grades") GradeForm gradeForm,
//                       HttpSession session){
//        List<GradeDTO> gradeList = gradeForm.getGrades();
//        Long subject_id = (Long) session.getAttribute("subject_id");
//        gradeService.save(gradeList, subject_id);
//        return "redirect:/quan-ly-diem/danh-sach";
//    }
    @PostMapping("/quan-ly-diem/luu")
    public String save(@ModelAttribute("gradeForm") GradeForm gradeForm, HttpSession session){
        if (gradeForm != null && gradeForm.getGrades() != null) {
            List<GradeDTO> gradeList = gradeForm.getGrades();
            Long subject_id = (Long) session.getAttribute("subject_id");
            gradeService.save(gradeList, subject_id);
            return "redirect:/giao-vien/quan-ly-diem/danh-sach?class_id=1&subject_id=1";
        } else {
            // handle error
            return null;
        }
    }
}
