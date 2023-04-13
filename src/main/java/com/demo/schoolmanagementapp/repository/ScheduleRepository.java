package com.demo.schoolmanagementapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.schoolmanagementapp.entity.Schedule;

public interface ScheduleRepository extends JpaRepository<Schedule, Long>{
    
}
