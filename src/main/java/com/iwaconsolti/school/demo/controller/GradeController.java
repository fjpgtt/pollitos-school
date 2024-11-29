package com.iwaconsolti.school.demo.controller;

import com.iwaconsolti.school.demo.model.Grade;
import com.iwaconsolti.school.demo.service.GradeService;
import com.iwaconsolti.school.demo.service.SchoolService;
import com.iwaconsolti.school.dto.GradeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/school")
public class GradeController {
    private final SchoolService schoolService;
    private final GradeService gradeService;

    @Autowired
    public GradeController(SchoolService schoolService, GradeService gradeService) {
        this.schoolService = schoolService;
        this.gradeService = gradeService;
    }

    @GetMapping("/{schoolName}/allGrades")
    public ResponseEntity<List<GradeDTO>> returnGrades(@PathVariable String schoolName) {
        return ResponseEntity.ok(gradeService.getGrades(schoolService.getSchoolByName(schoolName)));
    }

    @PostMapping("/newGrade")
    public ResponseEntity<String> newGrade(@RequestBody Grade grade){
        return ResponseEntity.ok(gradeService.createGrade(grade));
    }

    @PutMapping("/editGrade")
    public ResponseEntity<String> editGrade(@RequestBody Grade grade){
        System.out.println("Grade: "+ grade.toString());
        return ResponseEntity.ok(gradeService.updateGrade(grade));
    }

    @DeleteMapping("/{id}/eraseGrade")
    public ResponseEntity<String> eraseGrade(@PathVariable int id){
        System.out.println("idToDelete"+ id);
        return ResponseEntity.ok(gradeService.deleteGrade(id));
    }

    @DeleteMapping("/{idCourse}/eraseAllGradesOfCourse")
    public ResponseEntity<String> eraseAllGradesOfCourse(@PathVariable int idCourse){
        System.out.println("idToDelete"+ idCourse);
        return ResponseEntity.ok(gradeService.deleteAllGradesOfCourse(idCourse));
    }
}


