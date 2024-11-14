package com.iwaconsolti.school.miguel.controller;

import com.iwaconsolti.school.miguel.model.Students;
import com.iwaconsolti.school.miguel.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/app/{nameSchool}")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping("/student")
    public ResponseEntity<Students> registerStudent(@PathVariable String nameSchool, @RequestBody Students student) {

        if(student.getFirstName() == null || student.getLastName() == null || student.getAge() == 0){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(studentService.createStudents(nameSchool,student));

    }

    @GetMapping("/student")
    public ResponseEntity<Collection<Students>> listStudents(@PathVariable String nameSchool) {

        Collection<Students> students = studentService.getStudents(nameSchool);

        if (students.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        return ResponseEntity.ok(students);
    }

    @PutMapping("/student/{studentId}")
    public ResponseEntity<Students> editStudent(@PathVariable String nameSchool,@PathVariable int studentId,@RequestBody Students student){

        Students updatedStudent = studentService.editStudent(studentId, nameSchool, student);

        if(updatedStudent == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        return ResponseEntity.ok(updatedStudent);
    }
}