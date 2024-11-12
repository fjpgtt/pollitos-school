package com.iwaconsolti.school.controller;

import com.iwaconsolti.school.model.Grade;
import com.iwaconsolti.school.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/{schoolName}/grade")
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

    @GetMapping("/student/{studentId}")
    public List<Grade> getGradesByStudent(@PathVariable String schoolName, @PathVariable int studentId) {
        return getServiceBySchoolName(schoolName).getGradesByStudent(studentId);
    }

    @DeleteMapping("/student/{studentId}")
    public void deleteGradesByStudent(@PathVariable String schoolName, @PathVariable int studentId) {
        getServiceBySchoolName(schoolName).deleteGradesOfStudent(studentId);
    }

    @DeleteMapping("/course/{courseId}")
    public void deleteGradesByCourse(@PathVariable String schoolName, @PathVariable int courseId) {
        getServiceBySchoolName(schoolName).deleteGradesOfCourse(courseId);
    }
}
