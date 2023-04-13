package com.demo.schoolmanagementapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.schoolmanagementapp.entity.Subject;

public interface SubjectRepository extends JpaRepository<Subject, Long>{
    
}
