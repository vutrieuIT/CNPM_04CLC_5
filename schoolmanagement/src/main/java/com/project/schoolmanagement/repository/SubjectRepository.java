package com.project.schoolmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.schoolmanagement.entity.Subject;
import org.springframework.data.jpa.repository.Query;

public interface SubjectRepository extends JpaRepository<Subject, Long>{

    @Query("select s.name from Subject s where s.subject_id = ?1")
    String getNameById(Long id);
}
