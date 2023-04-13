package com.demo.schoolmanagementapp.service;

import org.springframework.stereotype.Service;

import com.demo.schoolmanagementapp.entity.Student;

@Service
public interface StudentService {
    Student getReferenceById(Long student_id);

    void save(Student student);

    void getPoints(Long student_id);
}