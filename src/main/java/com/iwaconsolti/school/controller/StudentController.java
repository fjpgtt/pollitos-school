package com.iwaconsolti.school.controller;

import com.iwaconsolti.school.controller.response.StudentResponse;
import com.iwaconsolti.school.model.Student;
import com.iwaconsolti.school.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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

    private StudentResponse convertToStudentResponse(Student student) {
        return new StudentResponse(student.getId(), student.getFirstName(), student.getLastName(), student.getAge());
    }

    @GetMapping
    public List<StudentResponse> getAllStudents(@PathVariable String schoolName) {
        List<Student> students = getServiceBySchoolName(schoolName).getStudents();
        return students.stream()
                .map(this::convertToStudentResponse)
                .collect(Collectors.toList());
    }

    @PutMapping("/{id}")
    public void editStudent(@PathVariable String schoolName, @PathVariable int id, @RequestBody Student updatedStudent) {
        getServiceBySchoolName(schoolName).editStudent(id, updatedStudent);
    }
}
