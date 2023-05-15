package com.project.schoolmanagement.service.Impl;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.schoolmanagement.entity.Grade;
import com.project.schoolmanagement.entity.Student;
import com.project.schoolmanagement.repository.GradeRepository;
import com.project.schoolmanagement.repository.StudentRepository;
import com.project.schoolmanagement.service.IStudentService;

@Service
public class StudentServiceImpl implements IStudentService {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    GradeRepository gradeRepository;

    @Override
    public Student getReferenceById(Long student_id) {
        return studentRepository.getReferenceById(student_id);
    }

    @Override
    public void save(Student student) {
        studentRepository.save(student);
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

    public Map<String, String> calculateAverageOfGroupGradesBySubject2(List<Grade> grades) {
        DecimalFormat decimalFormat = new DecimalFormat("#0.00");
        return grades.stream()
                .collect(Collectors.groupingBy(g -> g.getSubject().getName(),
                        Collectors.averagingDouble(Grade::getPoint)))
                .entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, e -> decimalFormat.format(e.getValue())));
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

    @Override
    public Grade getGradeById(Long id) {
        return gradeRepository.findById(id).get();
    }

    @Override
    public Grade saveGrade(Grade grade) {
        return gradeRepository.save(grade);
    }

    @Override
    public List<Grade> getAllGradesOfStudent(Long student_id) {
        return gradeRepository.getAllGrades(student_id);
    }

    

}
