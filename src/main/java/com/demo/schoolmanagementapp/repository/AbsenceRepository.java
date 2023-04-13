package com.demo.schoolmanagementapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.schoolmanagementapp.entity.Absence;

public interface AbsenceRepository extends JpaRepository<Absence, Long>{
    
}
