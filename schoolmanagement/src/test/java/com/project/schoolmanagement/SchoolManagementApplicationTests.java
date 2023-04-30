package com.project.schoolmanagement;

import com.project.schoolmanagement.dto.GradeDTO;
import com.project.schoolmanagement.entity.Grade;
import com.project.schoolmanagement.entity.Student;
import com.project.schoolmanagement.repository.ClassRepository;
import com.project.schoolmanagement.repository.GradeRepository;
import com.project.schoolmanagement.repository.StudentRepository;
import com.project.schoolmanagement.service.GradeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotNull;

@SpringBootTest
public class SchoolManagementApplicationTests {
    @Autowired
    private GradeRepository gradeRepository;

    @Autowired
    private GradeService gradeService;
    @Test
    void contextLoads() {
        List<GradeDTO> list = gradeService.getScoreTable(1L, 1L);
        for (GradeDTO dto : list){
            System.out.print(dto.getStudent_id() + " ");
            System.out.print(dto.getStudentName() + " ");
            System.out.println(dto.getGrade() + " ");
        }
    }

    @Test
    void luu(){
        List<GradeDTO> list = new ArrayList<>();
        GradeDTO dto = new GradeDTO();
        dto.setGrade(7.8f);
        dto.setStudent_id(4L);
        list.add(dto);
        gradeService.save(list, 1L);
    }
}
