package com.iwaconsolti.school.controller;

import com.iwaconsolti.school.controller.request.StudentRequest;
import com.iwaconsolti.school.controller.response.StudentResponse;
import com.iwaconsolti.school.model.School;
import com.iwaconsolti.school.model.Student;
import com.iwaconsolti.school.service.SchoolService;
import com.iwaconsolti.school.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/{schoolName}/student")
public class StudentController {

    private final StudentService gerardoStudentService;
    private final StudentService zetStudentService;
    private final SchoolService schoolService;

    @Autowired
    public StudentController(StudentService gerardoStudentService, StudentService zetStudentService, SchoolService schoolService) {
        this.gerardoStudentService = gerardoStudentService;
        this.zetStudentService = zetStudentService;
        this.schoolService = schoolService;
    }

    private StudentService getServiceBySchoolName(String schoolName) {
        if ("GerardoInstitute".equalsIgnoreCase(schoolName)) {
            return gerardoStudentService;
        } else if ("ZetCollege".equalsIgnoreCase(schoolName)) {
            return zetStudentService;
        } else {
            throw new IllegalArgumentException("Invalid school name");
        }
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
    public List<StudentResponse> findAllStudents(@PathVariable String schoolName) {
        return getServiceBySchoolName(schoolName)
                .getAllStudentsBySchool(schoolName)
                .stream()
                .map(this::convertStudentToResponse)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public StudentResponse findStudentById(@PathVariable String schoolName, @PathVariable int id) {
        return getServiceBySchoolName(schoolName)
                .getStudentById(id, schoolName)
                .map(this::convertStudentToResponse)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found in this school"));
    }

    @PostMapping
    public ResponseEntity<StudentResponse> createStudent(@PathVariable String schoolName,
                                                         @RequestBody StudentRequest studentRequest) {
        School school = schoolService.findByName(schoolName)
                .orElseThrow(() -> new IllegalArgumentException("School not found: " + schoolName));

        Student student = convertRequestToStudent(studentRequest, school);
        student = getServiceBySchoolName(schoolName).createStudent(student);
        StudentResponse studentResponse = convertStudentToResponse(student);

        return new ResponseEntity<>(studentResponse, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentResponse> updateStudent(
            @PathVariable String schoolName,
            @PathVariable int id,
            @RequestBody StudentRequest studentRequest) {

        StudentService studentService = getServiceBySchoolName(schoolName);

        School school = schoolService.findByName(schoolName)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid school name"));

        Student updatedStudent = convertRequestToStudent(studentRequest, school);
        Student savedStudent = studentService.updateStudent(id, updatedStudent, schoolName);
        StudentResponse studentResponse = convertStudentToResponse(savedStudent);

        return new ResponseEntity<>(studentResponse, HttpStatus.OK);
    }
}
