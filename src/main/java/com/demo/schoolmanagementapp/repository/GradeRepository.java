package com.demo.schoolmanagementapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.schoolmanagementapp.entity.Grade;

public interface GradeRepository extends JpaRepository<Grade, Long>{
    
}
