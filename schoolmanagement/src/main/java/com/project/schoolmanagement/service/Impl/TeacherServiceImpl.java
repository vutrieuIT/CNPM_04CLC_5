package com.project.schoolmanagement.service.Impl;

import com.project.schoolmanagement.dto.ClassSubjectByTeacherIdDTO;
import com.project.schoolmanagement.entity.Assign;
import com.project.schoolmanagement.entity.Class;
import com.project.schoolmanagement.entity.Notification;
import com.project.schoolmanagement.entity.Teacher;
import com.project.schoolmanagement.form.NotificationForm;
import com.project.schoolmanagement.repository.AssignRepository;
import com.project.schoolmanagement.repository.ClassRepository;
import com.project.schoolmanagement.repository.NotificationRepository;
import com.project.schoolmanagement.repository.TeacherRepository;
import com.project.schoolmanagement.service.ITeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TeacherServiceImpl implements ITeacherService {

    @Autowired
    private ClassRepository classRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private AssignRepository assignRepository;

    @Autowired
    private NotificationRepository notificationRepository;

    @Override
    public Teacher login(String username, String password) {
        return teacherRepository.findByUsernameAndPassword(username,password);
    }

    @Override
    public List<Class> getDSLop(Long id) {
        return classRepository.findClassesByTeacher_Id(id);
    }

    @Override
    public List<ClassSubjectByTeacherIdDTO> getDanhSachLop(Long id) {
        List<Object[]> list = classRepository.findClassesByTeacherId(id);
        List<ClassSubjectByTeacherIdDTO> res = new ArrayList<>();
        for (Object[] objects: list){
            res.add(
                    new ClassSubjectByTeacherIdDTO(
                            (Long) objects[0],
                            (String) objects[1],
                            (Long) objects[2],
                            (String) objects[3]
                    )
            );
        }
        return res;
    }

    @Override
    public void saveNotify(String message, Long class_id, Long subject_id, Long teacher_id) {
        Assign assign = assignRepository.findFirstAssignByClassAndSubjectAndTeacher(class_id,subject_id,teacher_id);
        Notification notification = new Notification();
        notification.setAssign(assign);
        notification.setContent(message);
        notificationRepository.save(notification);
    }

    @Override
    public List<NotificationForm> getNotifies(Long teacher_id) {
        List<Object[]> notifies = assignRepository.getNotify(teacher_id);
        List<NotificationForm> notifications = new ArrayList<>();
        for (Object[] object : notifies){
            NotificationForm notification = new NotificationForm();
            notification.setClassName((String) object[0]);
            notification.setSubjectName((String) object[1]);
            notification.setMessage((String) object[2]);
            notification.setDate((Date) object[3]);
            notifications.add(notification);
        }
        return notifications;
    }
}
