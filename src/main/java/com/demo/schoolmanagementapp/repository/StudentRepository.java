package com.demo.schoolmanagementapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.schoolmanagementapp.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long>{
    
}
