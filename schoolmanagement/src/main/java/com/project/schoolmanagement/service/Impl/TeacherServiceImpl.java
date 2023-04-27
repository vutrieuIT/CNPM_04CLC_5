package com.project.schoolmanagement.service.Impl;

import com.project.schoolmanagement.entity.Class;
import com.project.schoolmanagement.repository.ClassRepository;
import com.project.schoolmanagement.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private ClassRepository classRepository;
    @Override
    public List<Class> getDSLop(Long id) {
        return classRepository.findClassesByTeacher_Id(id);
    }
}
