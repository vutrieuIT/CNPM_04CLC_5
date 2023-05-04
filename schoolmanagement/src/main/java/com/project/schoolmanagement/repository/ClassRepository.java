package com.project.schoolmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.project.schoolmanagement.entity.Class;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClassRepository extends JpaRepository<Class, Long> {

    @Query("select c from Assign a join a.clazz c where a.teacher.teacher_id = :teacher_id")
    List<Class> findClassesByTeacher_Id(@Param("teacher_id") Long teacher_id);

//    tim id, ten cua lop va mon hoc
    @Query("SELECT c.class_id, c.name, s.subject_id, s.name " +
            "FROM Assign a JOIN a.clazz c JOIN a.subject s " +
            "where a.teacher.teacher_id = :teacher_id")
    List<Object[]> findClassesByTeacherId(@Param("teacher_id") Long teacher_id);


    @Query("select c from Class c where c.class_id = ?1")
    Class findByClass_id(Long class_id);
}
