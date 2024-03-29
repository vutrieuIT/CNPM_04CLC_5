package com.project.schoolmanagement.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.schoolmanagement.entity.Student;
import com.project.schoolmanagement.repository.StudentRepository;

@RestController
@RequestMapping("/api/students")
public class StudentRestController {

    @Autowired
    private final StudentRepository studentRepository;

    // constructor injection
    public StudentRestController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @GetMapping(value = "/list")
    public List<Student> getAllStudent() {
        List<Student> students = new ArrayList<>();
        students = studentRepository.findAll();
        System.out.println("students: " + students);
        return students;
    }
}
   