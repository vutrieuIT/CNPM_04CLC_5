package com.project.schoolmanagement.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.schoolmanagement.entity.Student;
import com.project.schoolmanagement.repository.StudentRepository;
import com.project.schoolmanagement.service.StudentService;
@Service
public class StudentServiceImpl implements StudentService{

    @Autowired
    StudentRepository repo;

    @Override
    public Student getReferenceById(Long student_id) {
        return repo.getReferenceById(student_id);
    }

    @Override
    public void save(Student student) {
        repo.save(student);
    }

    @Override
    public void getPoints(Long student_id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPoints'");
    }
    
}
