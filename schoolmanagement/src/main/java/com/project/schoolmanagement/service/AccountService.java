package com.project.schoolmanagement.service;

import org.springframework.stereotype.Service;

@Service
public interface AccountService {
    public boolean studentLogin(String username, String password);

    public boolean teacherLogin(String username, String password);

    public boolean adminLogin(String username, String password);
}
