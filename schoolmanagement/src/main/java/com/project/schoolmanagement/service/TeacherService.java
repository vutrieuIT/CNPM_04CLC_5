package com.project.schoolmanagement.service;

import com.project.schoolmanagement.dto.ClassSubjectByTeacherIdDTO;
import com.project.schoolmanagement.entity.Class;
import com.project.schoolmanagement.entity.Teacher;

import java.util.List;

public interface TeacherService {

    Teacher login(String username, String password);
    List<Class> getDSLop(Long id);

    List<ClassSubjectByTeacherIdDTO> getDanhSachLop(Long id);
}
