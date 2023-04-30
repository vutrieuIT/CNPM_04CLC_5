package com.project.schoolmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.schoolmanagement.entity.Teacher;
import org.springframework.data.jpa.repository.Query;

public interface TeacherRepository extends JpaRepository<Teacher, Long>{

    @Query("SELECT t FROM Teacher t WHERE t.username = ?1 AND t.password =?2")
    Teacher findByUsernameAndPassword(String username, String password);
}
