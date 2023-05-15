package com.project.schoolmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.schoolmanagement.entity.Grade;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GradeRepository extends JpaRepository<Grade, Long>{

    @Query("SELECT s.student_id, s.name, g.grade_id, g.point FROM Student s LEFT JOIN s.grades g " +
            "WHERE s.clazz.class_id = :class_id " +
            "AND (g.subject.subject_id = :subject_id OR g.subject.subject_id is null)")
    List<Object[]> getScoreTable(@Param("class_id") Long class_id,@Param("subject_id") Long subject_id);

    @Query("SELECT g FROM Grade g WHERE g.student.student_id = :student_id")
    //SELECT * FROM grade g WHERE g.student_id = 2
    List<Grade> getAllGrades(Long student_id);
}
