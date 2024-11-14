package com.iwaconsolti.school.controller;

import com.iwaconsolti.school.controller.response.StudentRequest;
import com.iwaconsolti.school.model.Student;
import com.iwaconsolti.school.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/{schoolName}/student")
public class StudentController {

    @Autowired
    @Qualifier("gerardoService")
    private SchoolService gerardoService;

    @Autowired
    @Qualifier("zetService")
    private SchoolService zetService;

    private SchoolService getServiceBySchoolName(String schoolName) {
        if ("GerardoInstitute".equalsIgnoreCase(schoolName)) {
            return gerardoService;
        } else if ("ZetCollege".equalsIgnoreCase(schoolName)) {
            return zetService;
        } else {
            throw new IllegalArgumentException("Invalid school name");
        }
    }

    private StudentRequest convertToStudentRequest(Student studentResponse) {
        return new StudentRequest(studentResponse.getId(),
                studentResponse.getFirstName(),
                studentResponse.getLastName(),
                studentResponse.getAge());
    }

    private Student convertToStudentResponse(StudentRequest studentRequest) {
        return new Student(studentRequest.getId(),
                studentRequest.getFirstName(),
                studentRequest.getLastName(),
                studentRequest.getAge());
    }

    @GetMapping
    public List<StudentRequest> findAllStudents(@PathVariable String schoolName) {
        List<Student> students = getServiceBySchoolName(schoolName).findStudents();
        return students.stream()
                .map(student -> new StudentRequest(student.getId(),
                        student.getFirstName(),
                        student.getLastName(),
                        student.getAge()))
                .collect(Collectors.toList());
    }

    @PostMapping
    public ResponseEntity<StudentRequest> createStudent(@PathVariable String schoolName, @RequestBody StudentRequest studentRequest) {
        Student studentResponse = convertToStudentResponse(studentRequest);
        if (getServiceBySchoolName(schoolName).createStudent(studentResponse) != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(studentRequest);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @PutMapping("/{id}")
    public void updateStudent(@PathVariable String schoolName, @PathVariable int id, @RequestBody StudentRequest updatedStudentRequest) {
        Student updatedStudent = convertToStudentResponse(updatedStudentRequest);
        getServiceBySchoolName(schoolName).updateStudent(id, updatedStudent);
    }
}
