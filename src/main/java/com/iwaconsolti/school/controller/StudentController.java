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
        List<Student> students = studentService.getStudents();
        return ResponseEntity.ok(students);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getStudentById(@PathVariable Integer id) {
        Student student = studentService.getStudentById(id);
        return ResponseEntity.ok(student);
    }

    @PostMapping
    public ResponseEntity<?> addStudent(@RequestBody Student student) {
        Student addStudent = studentService.addStudent(student);
        return ResponseEntity.status(201).body(addStudent);
    }

    @PutMapping
    public ResponseEntity<?> editStudent(@RequestBody Student student){
        Student setStudent = studentService.editStudent(student);
        return ResponseEntity.ok(setStudent);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable Integer id){
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }

}
