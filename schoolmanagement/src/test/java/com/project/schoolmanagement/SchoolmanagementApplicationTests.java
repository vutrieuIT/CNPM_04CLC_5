package com.project.schoolmanagement;

import com.project.schoolmanagement.entity.Assign;
import com.project.schoolmanagement.entity.Class;
import com.project.schoolmanagement.form.NotificationForm;
import com.project.schoolmanagement.repository.AbsenceRepository;
import com.project.schoolmanagement.repository.AssignRepository;
import com.project.schoolmanagement.repository.ScheduleRepository;
import com.project.schoolmanagement.service.IClassService;
import com.project.schoolmanagement.service.ITeacherService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class SchoolmanagementApplicationTests {

	@Autowired
	private ITeacherService teacherService;

	@Test
	void contextLoads() {
		List<NotificationForm> forms = teacherService.getNotifies(1L);
		System.out.println(forms.size());
	}

}
