package com.project.schoolmanagement.service.Impl;

import com.project.schoolmanagement.repository.ScheduleRepository;
import com.project.schoolmanagement.service.IScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleServiceImpl implements IScheduleService {

    @Autowired
    private ScheduleRepository scheduleRepository;
    @Override
    public String[][] findScheduleByClassId(Long class_id) {
        String[][] schedule = new String[5][6];
        List<Object[]> list = scheduleRepository.findSubjectName(class_id);
        for (Object[] object : list){
            Integer thu = (Integer) object[0];
            Integer tiet = (Integer) object[1];
            String tenmon = (String) object[2];
            schedule[tiet-1][thu-2] = tenmon;
        }
        return schedule;
    }
}
