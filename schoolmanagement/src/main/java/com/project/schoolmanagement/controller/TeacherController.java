package com.project.schoolmanagement.controller;

import com.project.schoolmanagement.dto.ClassSubjectByTeacherIdDTO;
import com.project.schoolmanagement.dto.GradeDTO;
import com.project.schoolmanagement.entity.Schedule;
import com.project.schoolmanagement.entity.Subject;
import com.project.schoolmanagement.entity.Teacher;
import com.project.schoolmanagement.form.GradeForm;
import com.project.schoolmanagement.repository.SubjectRepository;
import com.project.schoolmanagement.service.IClassService;
import com.project.schoolmanagement.service.IGradeService;
import com.project.schoolmanagement.service.IScheduleService;
import com.project.schoolmanagement.service.ITeacherService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/giao-vien")
public class TeacherController {

    @Autowired
    private ITeacherService ITeacherService;

    @Autowired
    private IGradeService gradeService;

    @Autowired
    private IClassService classService;

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private IScheduleService scheduleService;

    @GetMapping()
    public String home(Model model, HttpSession session){
        Teacher teacher = (Teacher) session.getAttribute("teacher");
        model.addAttribute("teacher", teacher);
        return "teacher/teacher-home";
    }

    @GetMapping("/thoi-khoa-bieu")
    public String getSchedule(Model model, HttpSession session) {
        Teacher teacher = (Teacher) session.getAttribute("teacher");
        String[][] schedule = scheduleService.findScheduleByTeacherId(teacher.getTeacher_id());
        model.addAttribute("teacher", teacher);
        model.addAttribute("tkb", schedule);
        return "teacher/teacher-schedule";
    }

    @GetMapping("/quan-ly-diem")
    public String getClassList(Model model, HttpSession session){
        Teacher teacher = (Teacher) session.getAttribute("teacher");
        List<ClassSubjectByTeacherIdDTO> dslop = new ArrayList<>();
        dslop = ITeacherService.getDanhSachLop(teacher.getTeacher_id());
        model.addAttribute("teacher", teacher);
        model.addAttribute("danhSach",dslop);
        return "/teacher/score";
    }

    @GetMapping("/quan-ly-diem/danh-sach")
    public String getScoreTable(@RequestParam(name = "class_id") Long class_id,
                                @RequestParam(name = "subject_id") Long subject_id,
                                Model model, HttpSession session){
        session.setAttribute("subject_id", subject_id);
        session.setAttribute("class_id", class_id);
        GradeForm gradeForm = new GradeForm();
        List<GradeDTO> scores = gradeService.getScoreTable(class_id, subject_id);
        gradeForm.setGrades(scores);
        if (!model.containsAttribute("gradeForm")) {
            model.addAttribute("gradeForm", new GradeForm());
        }
        Teacher teacher = (Teacher) session.getAttribute("teacher");
        model.addAttribute("class_name", classService.findClassNameById(class_id));
        model.addAttribute("subject_name", subjectRepository.getNameById(subject_id));
        model.addAttribute("teacher", teacher);
        model.addAttribute("gradeForm", gradeForm);
        return "teacher/control-score";
    }

    @PostMapping("/quan-ly-diem/luu")
    public String save(@ModelAttribute("gradeForm") GradeForm gradeForm, HttpSession session,
                       RedirectAttributes redirectAttributes){
        if (gradeForm != null && gradeForm.getGrades() != null) {
            List<GradeDTO> gradeList = gradeForm.getGrades();
            Long subject_id = (Long) session.getAttribute("subject_id");
            Long class_id = (Long) session.getAttribute("class_id");
            gradeService.save(gradeList, subject_id);
            redirectAttributes.addFlashAttribute("message", "đã lưu");
            String url = String.format("redirect:/giao-vien/quan-ly-diem/danh-sach?" +
                    "class_id=%s&subject_id=%s", class_id, subject_id);
            return url;
        } else {
            // handle error
            return null;
        }
    }
}
