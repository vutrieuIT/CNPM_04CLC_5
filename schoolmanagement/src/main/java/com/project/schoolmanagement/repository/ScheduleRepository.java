package com.project.schoolmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.schoolmanagement.entity.Schedule;

public interface ScheduleRepository extends JpaRepository<Schedule, Long>{
    
}
