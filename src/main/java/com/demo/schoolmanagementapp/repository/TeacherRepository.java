package com.demo.schoolmanagementapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.schoolmanagementapp.entity.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, Long>{
    
}
