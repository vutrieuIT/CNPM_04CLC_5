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
    StudentRepository studentRepository;

    @Override
    public Student getReferenceById(Long student_id) {
        return studentRepository.getReferenceById(student_id);
    }

    @Override
    public void save(Student student) {
        studentRepository.save(student);
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

    public Student findStudentByUsernameAndPassword(String username, String password) {
        // Duyệt qua danh sách các Student trong students
        List<Student> students = studentRepository.findAll();
        for (Student student : students) {
            // Nếu tìm thấy student tương ứng với username và password
            if(student.getUsername().equals(username) && student.getPassword().equals(password)) {
                // Trả về student đó
                return student;
            }
        }
        // Nếu không tìm thấy student tương ứng, trả về null
        return null;
    }

}
