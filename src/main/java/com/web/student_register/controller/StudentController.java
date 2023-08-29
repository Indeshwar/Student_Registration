package com.web.student_register.controller;

import com.web.student_register.Dto.StudentDto;
import com.web.student_register.Dto.UserDto;
import com.web.student_register.Service.CustomUserService;
import com.web.student_register.Service.StudentService;
import com.web.student_register.entity.Student;
import com.web.student_register.entity.User;
import com.web.student_register.response.LogInResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/v1")
public class StudentController {
    @Autowired
    private StudentService studentService;
    @Autowired
    private CustomUserService userService;
    
    @PostMapping("/saveUser")
    public ResponseEntity<User> registerUser(@RequestBody UserDto userDto){
        User user = userService.registerUser(userDto);
        return new ResponseEntity<>(user, HttpStatus.ACCEPTED);
    }

    @PostMapping("/user/logIn")
    public ResponseEntity<LogInResponse> userLogIn(@RequestBody UserDto user){
        LogInResponse response = userService.userLogIn(user);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("auth/save_student")
    public ResponseEntity<Student> saveStudent(@RequestBody StudentDto studentDto){
        Student student = studentService.saveStudent(studentDto);
        return new ResponseEntity<>(student, HttpStatus.OK);

    }

    @GetMapping("auth/get_students")
    public ResponseEntity<List<Student>> getStudents(){
        List<Student> students = studentService.getStudents();
        return new ResponseEntity<>(students, HttpStatus.OK);

    }

    @GetMapping("auth/student/{studentId}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long studentId){
        Student s = studentService.getStudentById(studentId);
        return  new ResponseEntity<>(s, HttpStatus.OK);
    }

   @PutMapping("auth/update")
    public ResponseEntity<Student> updateStudent(@RequestBody StudentDto s){
        Student student = studentService.updateStudent(s);
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @DeleteMapping("auth/delete/{studentId}")
    public void deleteStudent(@PathVariable Long studentId){
        studentService.deleteStudent(studentId);
    }

    @GetMapping("auth/cont-total-absent")
    public ResponseEntity<Integer> getTotalAbsentDaysInMonth(@RequestBody StudentDto studentDto){
        Integer totalDaysInMonth = studentService.getTotalAbsentDaysInMonth(studentDto);
        return new ResponseEntity<>(totalDaysInMonth, HttpStatus.OK);
    }

    @GetMapping("/hello")
    public String hello(){
        return "Hello Java Alliance";
    }

}
