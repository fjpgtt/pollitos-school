package com.iwaconsolti.school.controller;

import com.iwaconsolti.school.model.Grade;
import com.iwaconsolti.school.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/grades")
public class GradeController {

    @Autowired
    private GradeService gradeService;

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<Grade>> getGradesByStudent(@PathVariable Integer studentId) {
        return ResponseEntity.ok(gradeService.getGradesByStudent(studentId));
    }

    @DeleteMapping("/student/{studentId}")
    public ResponseEntity<Void> deleteAllGradesOfStudent(@PathVariable Integer studentId) {
        gradeService.deleteAllGradesOfStudent(studentId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/course/{courseId}")
    public ResponseEntity<Void> deleteAllGradesOfCourse(@PathVariable Integer courseId) {
        gradeService.deleteAllGradesOfCourse(courseId);
        return ResponseEntity.noContent().build();
    }
}
