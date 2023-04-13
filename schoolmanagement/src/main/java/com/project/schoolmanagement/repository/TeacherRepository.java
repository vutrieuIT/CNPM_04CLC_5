package com.project.schoolmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.schoolmanagement.entity.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, Long>{
    
}
