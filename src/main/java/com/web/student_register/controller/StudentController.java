package com.web.student_register.controller;

import com.web.student_register.Dto.StudentDto;
import com.web.student_register.Service.StudentService;
import com.web.student_register.entity.Month;
import com.web.student_register.entity.Student;
import com.web.student_register.entity.Week;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.ResponseExtractor;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @PostMapping("save_student")
    public ResponseEntity<Student> saveStudent(@RequestBody StudentDto studentDto){
        Student student = studentService.saveStudent(studentDto);
        return new ResponseEntity<>(student, HttpStatus.OK);

    }

    @GetMapping("/get_students")
    public ResponseEntity<List<Student>> getStudents(){
        List<Student> students = studentService.getStudents();
        return new ResponseEntity<>(students, HttpStatus.OK);

    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long studentId){
        Student s = studentService.getStudentById(studentId);
        return  new ResponseEntity<>(s, HttpStatus.OK);
    }

   @PutMapping("/update")
    public ResponseEntity<Student> updateStudent(@RequestBody StudentDto s){
        Student student = studentService.updateStudent(s);
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{studentId}")
    public void deleteStudent(@PathVariable Long studentId){
        studentService.deleteStudent(studentId);
    }
}
