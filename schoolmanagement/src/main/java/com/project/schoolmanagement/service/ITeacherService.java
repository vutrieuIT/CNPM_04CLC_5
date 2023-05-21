package com.project.schoolmanagement.service;

import com.project.schoolmanagement.dto.ClassSubjectByTeacherIdDTO;
import com.project.schoolmanagement.entity.Class;
import com.project.schoolmanagement.entity.Notification;
import com.project.schoolmanagement.entity.Teacher;
import com.project.schoolmanagement.form.NotificationForm;

import java.util.List;

public interface ITeacherService {

    Teacher login(String username, String password);
    List<Class> getDSLop(Long id);
    List<ClassSubjectByTeacherIdDTO> getDanhSachLop(Long id);
    void saveNotify(String message, Long class_id, Long subject_id, Long teacher_id);

    List<NotificationForm> getNotifies(Long teacher_id);
}
