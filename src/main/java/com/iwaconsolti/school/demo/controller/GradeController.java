package com.iwaconsolti.school.demo.controller;

import com.iwaconsolti.school.demo.model.Grade;
import com.iwaconsolti.school.demo.model.School;
import com.iwaconsolti.school.demo.service.GradeService;
import com.iwaconsolti.school.demo.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/school")
public class GradeController {
    SchoolService schoolService;
    GradeService gradeService;

    @Autowired
    public GradeController(SchoolService schoolService, GradeService gradeService) {
        this.schoolService = schoolService;
        this.gradeService = gradeService;
    }

    @PostMapping("/{schoolName}/grade")
    public String createStudent(
            @PathVariable String schoolName,
            @RequestParam int score,
            @RequestParam int studentId ,
            @RequestParam int courseId) {

        School school = schoolService.getSchoolByName(schoolName);
        Grade grade = new Grade(score, studentId, courseId);
        return gradeService.createGrade(school, grade);
    }

     @GetMapping("/{schoolName}/getGrades")
     public String returnGrades(@PathVariable String schoolName){ // Obtener la escuela según el nombre
          return gradeService.getGrades(schoolName);
    }

}
