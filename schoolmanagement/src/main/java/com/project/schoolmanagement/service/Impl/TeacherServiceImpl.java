package com.project.schoolmanagement.service.Impl;

import com.project.schoolmanagement.dto.ClassSubjectByTeacherIdDTO;
import com.project.schoolmanagement.entity.Class;
import com.project.schoolmanagement.entity.Teacher;
import com.project.schoolmanagement.repository.ClassRepository;
import com.project.schoolmanagement.repository.TeacherRepository;
import com.project.schoolmanagement.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private ClassRepository classRepository;

    @Autowired
    private TeacherRepository teacherRepository;

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
}
