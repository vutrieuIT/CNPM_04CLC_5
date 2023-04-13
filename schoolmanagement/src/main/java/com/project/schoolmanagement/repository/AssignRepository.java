package com.project.schoolmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.schoolmanagement.entity.Assign;

public interface AssignRepository extends JpaRepository<Assign, Long>{
    
}
