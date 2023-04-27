package com.project.schoolmanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.project.schoolmanagement.entity.Absence;
import com.project.schoolmanagement.entity.Student;

public interface AbsenceRepository extends JpaRepository<Absence, Long> {

    @Query("SELECT a FROM Absence a WHERE a.student = ?1")
    List<Absence> getAllAbsencesOfStudent(Student student);

}
