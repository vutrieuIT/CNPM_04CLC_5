package com.project.schoolmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.project.schoolmanagement.entity.Class;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClassRepository extends JpaRepository<Class, Long> {

    @Query("select c from Assign a join a.clazz c where a.teacher.teacher_id = :teacher_id")
    List<Class> findClassesByTeacher_Id(@Param("teacher_id") Long teacher_id);
}
