package com.project.schoolmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.schoolmanagement.entity.Assign;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AssignRepository extends JpaRepository<Assign, Long>{

    @Query("select a from Assign a where a.clazz.class_id = ?1 and a.subject.subject_id = ?2 " +
            "and a.teacher.teacher_id = ?3")
    Assign findFirstAssignByClassAndSubjectAndTeacher(Long classId, Long subjectId, Long teacherId);

    @Query("select c.name, s.name, n.content, n.creDate from Assign a " +
            "join a.teacher t join a.subject s join a.clazz c join a.notifications n " +
            "where t.teacher_id = ?1")
    List<Object[]> getNotify(Long teacher_id);
}
