package com.project.schoolmanagement.service;

import com.project.schoolmanagement.entity.Schedule;

import java.util.List;

public interface IScheduleService {
    String[][] findScheduleByClassId(Long class_id);
    String[][] findScheduleByTeacherId(Long teacher_id);
}
