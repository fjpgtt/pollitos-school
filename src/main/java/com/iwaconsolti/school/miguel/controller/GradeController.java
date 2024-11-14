package com.iwaconsolti.school.miguel.controller;

import com.iwaconsolti.school.miguel.model.Grade;
import com.iwaconsolti.school.miguel.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/app/{nameSchool}")
public class GradeController {

    @Autowired
    private GradeService gradeService;

    @PostMapping("/grade")
    public ResponseEntity<Grade> createGrade(@PathVariable String nameSchool, @RequestBody Grade grade){

        if(grade.getScore() < 0 || grade.getScore() > 100){
            return ResponseEntity.status(HttpStatus.CREATED).body(null);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(gradeService.createGrade(nameSchool, grade));
    }

    @DeleteMapping("/grade/student/{studentId}")
    public ResponseEntity<Boolean> deleteGradesByStudentId(@PathVariable String nameSchool,@PathVariable int studentId){

        boolean result = gradeService.deleteGradeByStudentId(nameSchool,studentId);

        if(!result){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(false);
        }
        return ResponseEntity.ok(true);
    }

    @DeleteMapping("/grade/course/{courseId}")
    public ResponseEntity<Boolean> deleteGradesByCourseId(@PathVariable String nameSchool,@PathVariable int courseId){
        boolean result = gradeService.deleteGradeByCourseId(nameSchool, courseId);

        if(!result){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(false);
        }
        return ResponseEntity.ok(true);
    }
}