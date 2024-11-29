package com.iwaconsolti.school.demo.controller;

import com.iwaconsolti.school.demo.model.Student;
import com.iwaconsolti.school.demo.service.SchoolService;
import com.iwaconsolti.school.demo.service.StudentService;
import com.iwaconsolti.school.dto.GradeDTO;
import com.iwaconsolti.school.dto.StudentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/school")
public class StudentController {

    private final SchoolService schoolService;
    private final StudentService studentService;

    @Autowired
    public StudentController(SchoolService schoolService, StudentService studentService) {
        this.schoolService = schoolService;
        this.studentService = studentService;
    }

    @GetMapping("/{schoolName}/allStudents")
    public ResponseEntity<List<StudentDTO>> returnStudents(@PathVariable String schoolName) {
        return ResponseEntity.ok(studentService.getStudents(schoolService.getSchoolByName(schoolName)));
    }

    @PostMapping("/newStudent")
    public ResponseEntity<String> newStudent(@RequestBody Student student){
        return ResponseEntity.ok(studentService.createStudent(student));
    }

    @PutMapping("/editStudent")
    public ResponseEntity<String> editStudent(@RequestBody Student student){
        return ResponseEntity.ok(studentService.updateStudent(student));
    }

    @DeleteMapping("/{id}/eraseStudent")
    public ResponseEntity<String> eraseStudent(@PathVariable int id){
        return ResponseEntity.ok(studentService.deleteStudent(id));
    }

    @DeleteMapping("/{idStudent}/eraseAllGradesOfStudent")
    public ResponseEntity<String> eraseAllGradesOfStudent(@PathVariable int idStudent){
        return ResponseEntity.ok(studentService.deleteAllGradesOfStudent(idStudent));
    }

    @GetMapping("/{schoolName}/{studentId}/allGradesOfStudent")
    public ResponseEntity <List<GradeDTO>> returnAllGradesOfStudent(@PathVariable String schoolName, @PathVariable int studentId) {
        return ResponseEntity.ok(studentService.getAllGradesOfStudent(studentId, schoolService.getSchoolByName(schoolName)));
    }
}

