package com.project.schoolmanagement.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.project.schoolmanagement.entity.Grade;
import com.project.schoolmanagement.entity.Student;

@Service
public interface StudentService {
    Student getReferenceById(Long student_id);

    void save(Student student);

    void getPoints(Long student_id);

    Map<String, List<Grade>> groupGradesBySubject(List<Grade> grades);

    Map<String, Double> calculateAverageOfGroupGradesBySubject(List<Grade> grades);

    Student findStudentByUsernameAndPassword(String username, String password);
}