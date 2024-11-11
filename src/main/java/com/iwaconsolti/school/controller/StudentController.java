package com.iwaconsolti.school.controller;

import com.iwaconsolti.school.model.Student;
import com.iwaconsolti.school.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/{schoolName}/students")
public class StudentController {

    @Autowired
    @Qualifier("gerardoServicePopulated")
    private SchoolService gerardoService;

    @Autowired
    @Qualifier("zetServicePopulated")
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

    @PostMapping
    public void createStudent(@PathVariable String schoolName, @RequestBody Student student) {
        getServiceBySchoolName(schoolName).addStudent(student);
    }

    @GetMapping
    public List<Student> getAllStudents(@PathVariable String schoolName) {
        return getServiceBySchoolName(schoolName).getAllStudents();
    }

    @PutMapping("/{id}")
    public void editStudent(@PathVariable String schoolName, @PathVariable Integer id, @RequestBody Student updatedStudent) {
        getServiceBySchoolName(schoolName).editStudent(id, updatedStudent);
    }
}
