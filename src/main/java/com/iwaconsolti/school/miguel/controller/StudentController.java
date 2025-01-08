package com.iwaconsolti.school.miguel.controller;
import com.iwaconsolti.school.miguel.persistence.model.School;
import com.iwaconsolti.school.miguel.persistence.model.Students;
import com.iwaconsolti.school.miguel.persistence.repository.SchoolRepository;
import com.iwaconsolti.school.miguel.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/app/{nameSchool}")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private SchoolRepository schoolRepository;

    @PostMapping("/student")
    public ResponseEntity<Object> registerStudent(@PathVariable String nameSchool, @RequestBody Students student){

        School school = schoolRepository.findByName(nameSchool);

        if(school == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    Map.of(
                            "message", "the school was not found",
                            "status", HttpStatus.NOT_FOUND
                    )
            );
        }

        if(student.getFirstName() == null || student.getLastName() == null || student.getAge() == 0){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    Map.of(
                            "message","Required data is missing",
                            "status",HttpStatus.BAD_REQUEST.value()
                    )
            );
        }

        Students saveStudent = studentService.createStudentsDB(nameSchool,student);

        return ResponseEntity.status(HttpStatus.CREATED).body(
                Map.of(
                        "message","The student registered successfully",
                        "status",HttpStatus.CREATED.value(),"student",saveStudent
                )
        );
    }

    @GetMapping("/student")
    public ResponseEntity<Object> getStudents(@PathVariable String nameSchool){

        School school = schoolRepository.findByName(nameSchool);

        if(school == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    Map.of(
                            "message", "the school was not found",
                            "status", HttpStatus.NOT_FOUND
                    )
            );
        }

        List<Students> students = studentService.getAllStudentsDB(nameSchool);

        if (students.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    Map.of(
                            "message", "no registered student found",
                            "status", HttpStatus.NOT_FOUND
                    )
            );
        }

        return ResponseEntity.ok(students);
    }

    @PutMapping("/student/{studentId}")
    public ResponseEntity<Object> editStudent(@PathVariable String nameSchool,@PathVariable int studentId,@RequestBody Students student){

        School school = schoolRepository.findByName(nameSchool);

        if(school == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    Map.of(
                            "message", "the school was not found",
                            "status", HttpStatus.NOT_FOUND
                    )
            );
        }

        Integer updatedStudent = studentService.editStudentDB(studentId, student);

        if(updatedStudent == 0){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    Map.of(
                           "message", "the student was not found",
                            "status", HttpStatus.NOT_FOUND
                    )
            );
        }

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(
                Map.of(
                        "message", "the student was successfully updated",
                        "status", HttpStatus.ACCEPTED
                )
        );
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<Object> getStudentById(@PathVariable String nameSchool,@PathVariable int studentId){

        School school = schoolRepository.findByName(nameSchool);

        if(school == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    Map.of(
                            "message", "the school was not found",
                            "status", HttpStatus.NOT_FOUND
                    )
            );
        }

        List<Students> getStudent = studentService.getStudentById(studentId);

        if(getStudent.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    Map.of(
                            "message", "the student was not found",
                            "status", HttpStatus.NOT_FOUND
                    )
            );
        }

        return ResponseEntity.ok(getStudent);
    }
}