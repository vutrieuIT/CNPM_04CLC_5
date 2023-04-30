package com.project.schoolmanagement.service.Impl;

import com.project.schoolmanagement.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.project.schoolmanagement.entity.Student;
import com.project.schoolmanagement.service.IAccountService;
import com.project.schoolmanagement.service.IStudentService;

@Service
public class AccountServiceImpl implements IAccountService {
    @Autowired
    private IStudentService studentService;

    @Autowired
    private TeacherRepository teacherRepository;

    @Override
    public Student studentLogin(String username, String password) {
        

        // Tìm kiếm student với username và password tương ứng trong StudentRepository
        Student student = studentService.findStudentByUsernameAndPassword(username, password);
        // Nếu student tồn tại, trả về true
        if (student != null) {
            return student;
        }
        // Nếu không tìm thấy student tương ứng, trả về false
        return null;
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
