package com.project.schoolmanagement.service;

import org.springframework.stereotype.Service;

import com.project.schoolmanagement.entity.Student;

@Service
public interface IAccountService {
    public Student studentLogin(String username, String password);

    public boolean teacherLogin(String username, String password);

    public boolean adminLogin(String username, String password);
}
