package com.web.student_register;

import com.web.student_register.Dto.StudentDto;
import com.web.student_register.Dto.UserDto;
import com.web.student_register.Service.CustomUserService;
import com.web.student_register.Service.StudentService;
import com.web.student_register.entity.User;
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

	@Autowired
	private CustomUserService userService;
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

//	@Test
//	public void getTotalAbsentDaysInMonth(){
//		StudentDto s = new StudentDto();
//		s.setStudentId(5L);
//		s.setYear(2023);
//		s.setMonthName("january");
//		Integer result = studentService.getTotalAbsentDaysInMonth(s);
//		Assertions.assertEquals(4, result);
//
//	}

	@Test
	public void testRegisterUser(){
		UserDto u = new UserDto();
		u.setUserName("gopal");
		u.setPassword("gopal");
		u.setRoleName("USER");

		User user = userService.registerUser(u);
		Assertions.assertEquals("gopal", user.getUserName());
	}

}
