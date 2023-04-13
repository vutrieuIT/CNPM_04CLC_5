package com.project.schoolmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.schoolmanagement.entity.Grade;

public interface GradeRepository extends JpaRepository<Grade, Long>{
    
}
