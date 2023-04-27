package com.project.schoolmanagement.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.schoolmanagement.entity.Absence;
import com.project.schoolmanagement.entity.Student;
import com.project.schoolmanagement.repository.AbsenceRepository;
import com.project.schoolmanagement.service.IAbsenceService;

@Service
public class AbsenceServiceImpl implements IAbsenceService {

    @Autowired
    private AbsenceRepository absenceRepository;

    @Override
    public void save(Absence absence) {
        absenceRepository.save(absence);
}

    @Override
    public List<Absence> getAllAbsencesOfStudent(Student student) {
        return absenceRepository.getAllAbsencesOfStudent(student);
    }
}