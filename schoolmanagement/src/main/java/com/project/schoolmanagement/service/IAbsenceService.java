package com.project.schoolmanagement.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.schoolmanagement.entity.Absence;
import com.project.schoolmanagement.entity.Student;
@Service
public interface IAbsenceService {

    // get all absences of a student
    List<Absence> getAllAbsencesOfStudent(Student student);

    void save(Absence absence);
    
}
