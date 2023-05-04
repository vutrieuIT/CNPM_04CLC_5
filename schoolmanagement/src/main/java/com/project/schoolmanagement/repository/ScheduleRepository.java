package com.project.schoolmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.schoolmanagement.entity.Schedule;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long>{

    @Query("SELECT s.thu, s.tiet, sj.name  " +
            "FROM Schedule s " +
            "join s.assign a " +
            "join a.subject sj " +
            "where a.clazz.class_id = ?1")
    List<Object[]> findSubjectName(Long class_id);
    
}
