package com.iwaconsolti.school.miguel.controller;

import com.iwaconsolti.school.miguel.model.Students;
import com.iwaconsolti.school.miguel.model.dto.StudentsDTO;
import com.iwaconsolti.school.miguel.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Map;

@RestController
@RequestMapping("/app/{nameSchool}/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping("/")
    public ResponseEntity<Object> registerStudent(@PathVariable String nameSchool, @RequestBody StudentsDTO studentDTO) {

        if(studentDTO.getFirstName() == null || studentDTO.getLastName() == null || studentDTO.getAge() == 0){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    Map.of("message","Required data is missing")
            );
        }

        if(studentService.getStudentById(nameSchool,studentDTO.getId()) != null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    Map.of("message","The provided id is already registered")
            );
        }

        Students students = studentService.createStudents(nameSchool,studentDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                Map.of("message","The student registered successfully")
        );
    }

    @GetMapping("/")
    public ResponseEntity<Object> listStudents(@PathVariable String nameSchool) {

        Collection<StudentsDTO> students = studentService.getStudents(nameSchool);

        if (students.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    Map.of(
                            "message","no registered students found"
                    )
            );
        }

        return ResponseEntity.ok(students);
    }

    @GetMapping("/{studentId}")
    public ResponseEntity<Object> getStudentById(@PathVariable String nameSchool,@PathVariable int studentId){

        StudentsDTO getStudent = studentService.getStudentById(nameSchool,studentId);

        if(getStudent == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    Map.of(
                            "message","The students were not found"
                    )
            );
        }

        return ResponseEntity.ok(getStudent);
    }

    @PutMapping("/{studentId}")
    public ResponseEntity<Object> editStudent(@PathVariable String nameSchool,@PathVariable int studentId,@RequestBody StudentsDTO studentDTO){

        Students updatedStudent = studentService.editStudent(studentId, nameSchool, studentDTO);

        if(updatedStudent == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    Map.of(
                            "message", "The students were not found"
                    )
            );
        }

        return ResponseEntity.ok(updatedStudent);
    }
}