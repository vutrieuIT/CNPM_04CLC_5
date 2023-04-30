package com.project.schoolmanagement.service.Impl;

import com.project.schoolmanagement.dto.GradeDTO;
import com.project.schoolmanagement.entity.Grade;
import com.project.schoolmanagement.entity.Subject;
import com.project.schoolmanagement.repository.GradeRepository;
import com.project.schoolmanagement.repository.StudentRepository;
import com.project.schoolmanagement.repository.SubjectRepository;
import com.project.schoolmanagement.service.IGradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GradeServiceImpl implements IGradeService {

    @Autowired
    private GradeRepository gradeRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public List<GradeDTO> getScoreTable(Long class_id, Long subject_id) {
        List<Object[]> list = gradeRepository.getScoreTable(class_id, subject_id);
        List<GradeDTO> res = new ArrayList<>();
        for (Object[] objects : list){
            GradeDTO dto = new GradeDTO(
                    (Long) objects[0],
                    (String) objects[1],
                    (Long) objects[2],
                    (Float) objects[3]
            );
            res.add(dto);
        }
        return res;
    }

    @Override
    public void save(List<GradeDTO> list, Long subject_id) {
        Subject subject = subjectRepository.findById(subject_id).get();
         for (GradeDTO dto :list){
            Grade grade = new Grade();
            grade.setPoint(dto.getGrade());
            grade.setSubject(subject);
            grade.setGrade_id(dto.getGrade_id());
            grade.setStudent(
                    studentRepository.findById(dto.getStudent_id()).get()
            );
            gradeRepository.save(grade);
        }
    }
}
