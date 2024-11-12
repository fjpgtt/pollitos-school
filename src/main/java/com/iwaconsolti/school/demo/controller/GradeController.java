package com.iwaconsolti.school.demo.controller;

import com.iwaconsolti.school.demo.model.Grade;
import com.iwaconsolti.school.demo.model.Student;
import com.iwaconsolti.school.demo.service.GradeService;
import com.iwaconsolti.school.demo.service.StudentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/grades")
@Slf4j
@RequiredArgsConstructor
public class GradeController {

    private final GradeService gradeService;

    @GetMapping()
    public ResponseEntity<List<Grade>> getGrades(){
        log.info("Getting all the grades");
        return ResponseEntity.ok(gradeService.getGrades());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Grade> getGradeById(@PathVariable int id) {
        Grade grade = this.gradeService.getById(id);
        log.info("Id: {}", id);
        log.info("grade: {}", grade);
        if (grade != null)
            return ResponseEntity.ok(grade);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PostMapping()
    public ResponseEntity<Grade> getGrade(@RequestBody Grade grade){
        log.info("Creating the grade");
        grade = this.gradeService.createGrade(grade);
        return ResponseEntity.ok(grade);
    }

}
