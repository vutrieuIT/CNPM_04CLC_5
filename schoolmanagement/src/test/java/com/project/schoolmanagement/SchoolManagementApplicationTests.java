// package com.project.schoolmanagement;

// import com.project.schoolmanagement.entity.Class;
// import com.project.schoolmanagement.repository.ScheduleRepository;
// import com.project.schoolmanagement.service.IClassService;
// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.context.SpringBootTest;

// import java.util.List;

// @SpringBootTest
// class SchoolmanagementApplicationTests {

// 	@Autowired
// 	private IClassService classService;

// 	@Test
// 	void contextLoads() {
// 		List<Class> classes = classService.findAllClass();
// 		for (Class c:classes){
// 			System.out.print(c.getClass_id());
// 			System.out.println(c.getName());
// 		}
// 	}

// }
