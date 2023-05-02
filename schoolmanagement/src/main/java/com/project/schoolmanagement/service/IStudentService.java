package com.project.schoolmanagement.service;

import java.util.List;
import java.util.Map;

import com.project.schoolmanagement.entity.Grade;
import com.project.schoolmanagement.entity.Student;

public interface IStudentService {
    Student getReferenceById(Long student_id);

    void save(Student student);

    // List<Grade> getAllGrades(Long student_id);

    Map<String, List<Grade>> groupGradesBySubject(List<Grade> grades);

    Map<String, Double> calculateAverageOfGroupGradesBySubject(List<Grade> grades);

    Student findStudentByUsernameAndPassword(String username, String password);
}