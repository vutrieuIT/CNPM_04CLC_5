package com.project.schoolmanagement.service;

import com.project.schoolmanagement.entity.Student;
import com.project.schoolmanagement.entity.Teacher;

public interface IAccountService {
    public Student studentLogin(String username, String password);

    public Teacher teacherLogin(String username, String password);

    public boolean adminLogin(String username, String password);
}
