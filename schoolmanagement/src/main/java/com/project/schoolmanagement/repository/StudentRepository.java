package com.project.schoolmanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.project.schoolmanagement.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long>{

    // @Query("SELECT s FROM Student s WHERE s.clazz.class_id = :class_id")
    // List<Student> findByClazzClass_id(Long class_id);
}
