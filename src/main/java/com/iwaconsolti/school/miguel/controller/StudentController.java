package com.iwaconsolti.school.miguel.controller;

import com.iwaconsolti.school.miguel.model.Students;
import com.iwaconsolti.school.miguel.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Map;

@RestController
@RequestMapping("/app/{nameSchool}")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping("/student")
    public ResponseEntity<Object> registerStudent(@PathVariable String nameSchool, @RequestBody Students student) {

        if(student.getFirstName() == null || student.getLastName() == null || student.getAge() == 0){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    Map.of("message","Required data is missing","status",HttpStatus.BAD_REQUEST.value())
            );
        } else if(studentService.getStudentById(nameSchool,student.getId()) != null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    Map.of("message","The provided id is already registered","status",HttpStatus.BAD_REQUEST.value())
            );
        }
        Students students = studentService.createStudents(nameSchool,student);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                Map.of("message","The student registered successfully","status",HttpStatus.CREATED.value())
        );
        //
    }

    @GetMapping("/student")
    public ResponseEntity<Collection<Students>> listStudents(@PathVariable String nameSchool) {

        Collection<Students> students = studentService.getStudents(nameSchool);

        if (students.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        return ResponseEntity.ok(students);
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<Students> getStudentById(@PathVariable String nameSchool,@PathVariable int studentId){

        Students getStudent = studentService.getStudentById(nameSchool,studentId);

        if(getStudent == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        return ResponseEntity.ok(getStudent);
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