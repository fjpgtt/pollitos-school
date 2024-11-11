package com.iwaconsolti.school.controller;

import com.iwaconsolti.school.model.Student;
import com.iwaconsolti.school.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping
    public ResponseEntity<?> getStudents() {
        return ResponseEntity.ok(studentService.getStudents());
    }

    @PutMapping
    public ResponseEntity<?> editStudent(@RequestBody Student student){
        return ResponseEntity.ok(studentService.editStudent(student));
    }

}
