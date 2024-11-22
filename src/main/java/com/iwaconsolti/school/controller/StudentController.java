package com.iwaconsolti.school.controller;

import com.iwaconsolti.school.controller.request.StudentRequest;
import com.iwaconsolti.school.controller.response.StudentResponse;
import com.iwaconsolti.school.model.School;
import com.iwaconsolti.school.model.Student;
import com.iwaconsolti.school.service.SchoolService;
import com.iwaconsolti.school.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/{schoolName}/student")
public class StudentController {

    private final StudentService studentService;
    private final SchoolService schoolService;

    @Autowired
    public StudentController(StudentService studentService, SchoolService schoolService) {
        this.studentService = studentService;
        this.schoolService = schoolService;
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

    public School findSchool(String schoolName) {
        return schoolService.findByName(schoolName)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid school name"));
    }

    @GetMapping
    public List<StudentResponse> findAllStudents(@PathVariable String schoolName) {
        School school = findSchool(schoolName);

        return studentService
                .getAllStudentsBySchool(school.getId())
                .stream()
                .map(this::convertStudentToResponse)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public StudentResponse findStudentById(@PathVariable String schoolName, @PathVariable int id) {
        School school = findSchool(schoolName);

        return studentService
                .getStudentById(id, school.getId())
                .map(this::convertStudentToResponse)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found in this school"));
    }

    @PostMapping
    public ResponseEntity<StudentResponse> createStudent(@PathVariable String schoolName,
                                                         @RequestBody StudentRequest studentRequest) {
        School school = findSchool(schoolName);
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

        School school = findSchool(schoolName);
        Student updatedStudent = convertRequestToStudent(studentRequest, school);
        Student savedStudent = studentService.updateStudent(id, updatedStudent, school.getId());
        StudentResponse studentResponse = convertStudentToResponse(savedStudent);

        return new ResponseEntity<>(studentResponse, HttpStatus.OK);
    }
}
