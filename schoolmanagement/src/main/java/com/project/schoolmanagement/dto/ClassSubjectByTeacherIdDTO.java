package com.project.schoolmanagement.dto;

public class ClassSubjectByTeacherIdDTO {
    public Long class_id;
    public String className;
    public Long subject_id;
    public String subjectName;

    public ClassSubjectByTeacherIdDTO(Long class_id, String className, Long subject_id, String subjectName) {
        this.class_id = class_id;
        this.className = className;
        this.subject_id = subject_id;
        this.subjectName = subjectName;
    }

}
