package com.iwaconsolti.school.demo.controller;

import com.iwaconsolti.school.demo.model.RequestDemo;
import com.iwaconsolti.school.demo.model.Student;
import com.iwaconsolti.school.demo.service.DemoService;
import com.iwaconsolti.school.demo.service.StudentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
@Slf4j
@RequiredArgsConstructor
public class StudentsController {

    private final StudentService studentService;

    @GetMapping()
    public ResponseEntity<List<Student>> getStudents(){
        log.info("Getting all the students");
        return ResponseEntity.ok(studentService.getStudents());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable int id) {
        Student student = this.studentService.getById(id);
        log.info("Id: {}", id);
        log.info("Student: {}", student);
        if (student != null)
            return ResponseEntity.ok(student);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PostMapping()
    public ResponseEntity<Student> getStudents(@RequestBody Student student){
        log.info("Creating the student");
        student = this.studentService.createStudent(student);
        return ResponseEntity.ok(student);
    }

}
