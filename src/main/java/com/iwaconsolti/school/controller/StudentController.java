package com.iwaconsolti.school.controller;

import com.iwaconsolti.school.controller.request.StudentRequest;
import com.iwaconsolti.school.controller.response.StudentResponse;
import com.iwaconsolti.school.model.School;
import com.iwaconsolti.school.model.Student;
import com.iwaconsolti.school.service.SchoolHelperService;
import com.iwaconsolti.school.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/{schoolName}/student")
public class StudentController {

    private final StudentService studentService;
    private final SchoolHelperService schoolHelperService;

    @Autowired
    public StudentController(StudentService studentService, SchoolHelperService schoolHelperService) {
        this.studentService = studentService;
        this.schoolHelperService = schoolHelperService;
    }

    private Student convertRequestToStudent(StudentRequest studentRequest, School school) {
        return new Student(
                studentRequest.getFirstName(),
                studentRequest.getLastName(),
                studentRequest.getAge(),
                school
        );
    }

    private StudentResponse convertStudentToResponse(Student student) {
        return new StudentResponse(
                student.getId(),
                student.getFirstName(),
                student.getLastName(),
                student.getAge()
        );
    }

    @GetMapping
    public ResponseEntity<List<StudentResponse>> findAllStudents(@PathVariable String schoolName) {
        School school = schoolHelperService.findSchool(schoolName);

        List<StudentResponse> students = studentService
                .getAllStudentsBySchool(school.getId())
                .stream()
                .map(this::convertStudentToResponse)
                .toList();

        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentResponse> findStudentById(@PathVariable String schoolName, @PathVariable int id) {
        School school = schoolHelperService.findSchool(schoolName);

        return studentService
                .getStudentById(id, school.getId())
                .map(this::convertStudentToResponse)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }


    @PostMapping
    public ResponseEntity<StudentResponse> createStudent(@PathVariable String schoolName,
                                                         @RequestBody StudentRequest studentRequest) {
        School school = schoolHelperService.findSchool(schoolName);
        Student student = convertRequestToStudent(studentRequest, school);
        student = studentService.createStudent(student);
        StudentResponse studentResponse = convertStudentToResponse(student);

        return new ResponseEntity<>(studentResponse, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentResponse> updateStudent(
            @PathVariable String schoolName,
            @PathVariable int id,
            @RequestBody StudentRequest studentRequest) {

        School school = schoolHelperService.findSchool(schoolName);
        Student updatedStudent = convertRequestToStudent(studentRequest, school);
        Student savedStudent = studentService.updateStudent(id, updatedStudent, school.getId());
        StudentResponse studentResponse = convertStudentToResponse(savedStudent);

        return new ResponseEntity<>(studentResponse, HttpStatus.OK);
    }
}
