package com.project.schoolmanagement.service.Impl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.schoolmanagement.entity.Grade;
import com.project.schoolmanagement.entity.Student;
import com.project.schoolmanagement.repository.StudentRepository;
import com.project.schoolmanagement.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {

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

    @Override
    public Map<String, List<Grade>> groupGradesBySubject(List<Grade> grades) {
        return grades.stream().collect(Collectors.groupingBy(g -> g.getSubject().getName()));
    }

    @Override
    public Map<String, Double> calculateAverageOfGroupGradesBySubject(List<Grade> grades) {
        return grades.stream()
                .collect(Collectors.groupingBy(g -> g.getSubject().getName(),
                        Collectors.averagingDouble(Grade::getPoint)));
    }

}
