package com.project.schoolmanagement.service;

import com.project.schoolmanagement.entity.Class;

import java.util.List;

public interface IClassService {
    List<Class> findAllClass();

    String findClassNameById(Long id);
}
