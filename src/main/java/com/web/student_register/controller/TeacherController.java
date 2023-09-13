package com.web.student_register.controller;

import com.web.student_register.Service.StudentService;
import com.web.student_register.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/teacher")
public class TeacherController {
    @Autowired
    private StudentService studentService;

    @GetMapping("/get_students")
    public ResponseEntity<List<Student>> getStudents(){
        List<Student> students = studentService.getStudents();
        return new ResponseEntity<>(students, HttpStatus.OK);

    }

    @GetMapping("/hello")
    public String hello(){
        return "Hello Java Alliance";
    }
}
