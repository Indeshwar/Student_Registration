package com.web.student_register;

import com.web.student_register.Dto.StudentDto;
import com.web.student_register.Service.StudentService;
import com.web.student_register.entity.Week;
import org.assertj.core.api.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class StudentRegisterApplicationTests {
	@Autowired
	private StudentService studentService;
	@Test
	void contextLoads() {
	}

//	@Test
//	public void testStudentUpdate(){
//		StudentDto s = new StudentDto();
//		s.setStudentId(1L);
//		s.setWeekNum(1);
//		s.setDayName("sunday");
//
//		Week w = studentService.updateStudent(s);
//
//		//Assertions.assertEquals(1, w.getWeekNum());
//
//
//	}

}
