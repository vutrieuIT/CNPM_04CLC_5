package com.demo.schoolmanagementapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.schoolmanagementapp.entity.Assign;

public interface AssignRepository extends JpaRepository<Assign, Long>{
    
}
