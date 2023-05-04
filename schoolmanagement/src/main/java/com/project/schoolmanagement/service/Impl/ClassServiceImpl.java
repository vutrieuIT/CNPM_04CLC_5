package com.project.schoolmanagement.service.Impl;

import com.project.schoolmanagement.entity.Class;
import com.project.schoolmanagement.repository.ClassRepository;
import com.project.schoolmanagement.service.IClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassServiceImpl implements IClassService {

    @Autowired
    private ClassRepository classRepository;
    @Override
    public List<Class> findAllClass() {
        List<Class> list = classRepository.findAll();
        return list;
    }

    @Override
    public String findClassNameById(Long id) {
        return classRepository.findByClass_id(id).getName();
    }
}
