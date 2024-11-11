package com.iwaconsolti.school.controller;

import com.iwaconsolti.school.model.Grade;
import com.iwaconsolti.school.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/{schoolName}/grades")
public class GradeController {

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
    public void createGrade(@PathVariable String schoolName, @RequestBody Grade grade) {
        getServiceBySchoolName(schoolName).addGrade(grade);
    }

    @GetMapping("/students/{studentId}")
    public List<Grade> getGradesByStudent(@PathVariable String schoolName, @PathVariable Integer studentId) {
        return getServiceBySchoolName(schoolName).getGradesByStudent(studentId);
    }

    @DeleteMapping("/students/{studentId}")
    public void deleteGradesByStudent(@PathVariable String schoolName, @PathVariable Integer studentId) {
        getServiceBySchoolName(schoolName).deleteGradesByStudent(studentId);
    }

    @DeleteMapping("/courses/{courseId}")
    public void deleteGradesByCourse(@PathVariable String schoolName, @PathVariable Integer courseId) {
        getServiceBySchoolName(schoolName).deleteGradesByCourse(courseId);
    }
}
