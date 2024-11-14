package com.iwaconsolti.school.controller;

import com.iwaconsolti.school.controller.response.StudentResponse;
import com.iwaconsolti.school.model.Student;
import com.iwaconsolti.school.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.function.EntityResponse;

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

    private StudentResponse convertToStudentResponse(Student student) {
        return new StudentResponse(student.getId(),
                student.getFirstName(),
                student.getLastName(),
                student.getAge());
    }

    private Student convertToStudent(StudentResponse studentResponse) {
        return new Student(studentResponse.getId(),
                studentResponse.getFirstName(),
                studentResponse.getLastName(),
                studentResponse.getAge());
    }

    @GetMapping
    public List<StudentResponse> findAllStudents(@PathVariable String schoolName) {
        List<Student> students = getServiceBySchoolName(schoolName).findStudents();
        return students.stream()
                .map(student -> new StudentResponse(student.getId(),
                        student.getFirstName(),
                        student.getLastName(),
                        student.getAge()))
                .collect(Collectors.toList());
    }

    @PostMapping
    public ResponseEntity<StudentResponse> createStudent(@PathVariable String schoolName, @RequestBody StudentResponse studentResponse) {
        Student student = convertToStudent(studentResponse);
        if (getServiceBySchoolName(schoolName).createStudent(student) != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(studentResponse);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @PutMapping("/{id}")
    public void updateStudent(@PathVariable String schoolName, @PathVariable int id, @RequestBody StudentResponse updatedStudentResponse) {
        Student updatedStudent = convertToStudent(updatedStudentResponse);
        getServiceBySchoolName(schoolName).updateStudent(id, updatedStudent);
    }
}
