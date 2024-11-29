package com.iwaconsolti.school.demo.controller;

import com.iwaconsolti.school.demo.entity.GradeEntity;
import com.iwaconsolti.school.demo.model.Grade;
import com.iwaconsolti.school.demo.service.GradeInterface;
import com.iwaconsolti.school.demo.service.GradeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/grades")
@Slf4j
@RequiredArgsConstructor
@Profile("populated")
public class GradeController {

    private final GradeService gradeService;

    //H2
    @Autowired
    GradeInterface gradeInterface;

    //H2
    @GetMapping("/findAllGrades")
    public List<GradeEntity> findAllGrades(){
        return gradeInterface.findAllGrades();
    }

    //////////////////////////////////////////////////////////////////////////////////////
    @GetMapping()
    public ResponseEntity<List<Grade>> getGrades(){
        log.info("Getting all the grades");
        return ResponseEntity.ok(gradeService.getGrades());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Grade> findGradeById(@PathVariable int id) {
        Grade grade = this.gradeService.getById(id);
        log.info("Finding grade with Id: {}", id);
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
