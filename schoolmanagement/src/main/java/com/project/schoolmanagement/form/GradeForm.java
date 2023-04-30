package com.project.schoolmanagement.form;

import com.project.schoolmanagement.dto.GradeDTO;
import jogamp.nativewindow.windows.GDI;

import java.util.List;

public class GradeForm {
    private List<GradeDTO> grades;

    public List<GradeDTO> getGrades() {
        return grades;
    }

    public void setGrades(List<GradeDTO> grades) {
        this.grades = grades;
    }
}
