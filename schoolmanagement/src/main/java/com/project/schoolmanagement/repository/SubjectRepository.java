package com.project.schoolmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.schoolmanagement.entity.Subject;

public interface SubjectRepository extends JpaRepository<Subject, Long>{
    
}
