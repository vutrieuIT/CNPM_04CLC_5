package com.project.schoolmanagement.service.Impl;

import com.project.schoolmanagement.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.schoolmanagement.entity.Student;
import com.project.schoolmanagement.service.AccountService;
import com.project.schoolmanagement.service.StudentService;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private StudentService studentService;

    @Autowired
    private TeacherRepository teacherRepository;

    @Override
    public boolean studentLogin(String username, String password) {
        // ! Tài khoản mặc định
        if (username.equals("st") && password.equals("st")) {
            return true;
        }

        // Tìm kiếm student với username và password tương ứng trong StudentRepository
        Student student = studentService.findStudentByUsernameAndPassword(username, password);
        // Nếu student tồn tại, trả về true
        if (student != null) {
            return true;
        }
        // Nếu không tìm thấy student tương ứng, trả về false
        return false;
    }

    @Override
    public boolean teacherLogin(String username, String password) {
        if (teacherRepository.findByUsernameAndPassword(username,password) != null) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean adminLogin(String username, String password) {
        if (username.equals("admin") && password.equals("admin")) {
            return true;
        } else {
            return false;
        }
    }

}
