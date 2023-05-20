package com.project.schoolmanagement;

import com.project.schoolmanagement.entity.Class;
import com.project.schoolmanagement.repository.ScheduleRepository;
import com.project.schoolmanagement.repository.SubjectRepository;
import com.project.schoolmanagement.service.IClassService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class SchoolmanagementApplicationTests {

	@Autowired
	private SubjectRepository subjectRepository;

	@Test
	void contextLoads() {
		System.out.println(subjectRepository.getNameById(1L));
	}

}
