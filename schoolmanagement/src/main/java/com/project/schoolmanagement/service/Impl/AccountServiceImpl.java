package com.project.schoolmanagement.service.Impl;

import org.springframework.stereotype.Service;

import com.project.schoolmanagement.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {

    @Override
    public boolean studentLogin(String username, String password) {
        if (username.equals("student") && password.equals("student")) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean teacherLogin(String username, String password) {
        if (username.equals("teacher") && password.equals("teacher")) {
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
