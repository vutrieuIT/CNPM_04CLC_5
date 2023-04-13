package com.project.schoolmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.schoolmanagement.entity.Absence;

public interface AbsenceRepository extends JpaRepository<Absence, Long>{
    
}
