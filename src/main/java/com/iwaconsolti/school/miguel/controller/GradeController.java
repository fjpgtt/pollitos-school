package com.iwaconsolti.school.miguel.controller;

import com.iwaconsolti.school.miguel.model.Grade;
import com.iwaconsolti.school.miguel.model.dto.GradeStudentDTO;
import com.iwaconsolti.school.miguel.model.dto.GradesDTO;
import com.iwaconsolti.school.miguel.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/app/{nameSchool}/grade")
public class GradeController {

    @Autowired
    private GradeService gradeService;

    @Value("${school.score.limit:100}")
    private int limitScore;

    @PostMapping("/")
    public ResponseEntity<Object> createGrade(@PathVariable String nameSchool, @RequestBody GradesDTO gradeDTO){

        if(gradeDTO.getScore() < 0 || gradeDTO.getScore() > limitScore){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    Map.of(
                            "message","The score is not valid"
                    )
            );
        }
        Grade grade = gradeService.createGrade(nameSchool, gradeDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                Map.of(
                        "message", "The qualification was successfully registered",
                        "grade", grade
                )
        );
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<Object> getGradesByStudentId(@PathVariable String nameSchool, @PathVariable int studentId){
        List<GradeStudentDTO> gradeByStudentId = gradeService.getGradesByStudentId(nameSchool,studentId);

        if(gradeByStudentId.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    Map.of(
                            "message", "no student grade found"
                    )
            );
        }
        return ResponseEntity.ok(gradeByStudentId);
    }

    @DeleteMapping("/student/{studentId}")
    public ResponseEntity<Object> deleteGradesByStudentId(@PathVariable String nameSchool,@PathVariable int studentId){
        boolean result = gradeService.deleteGradeByStudentId(nameSchool,studentId);

        if(!result){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    Map.of(
                            "message", "student id not found"
                    )
            );
        }
        return ResponseEntity.ok(true);
    }

    @DeleteMapping("/course/{courseId}")
    public ResponseEntity<Object> deleteGradesByCourseId(@PathVariable String nameSchool,@PathVariable int courseId){
        boolean result = gradeService.deleteGradeByCourseId(nameSchool, courseId);

        if(!result){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    Map.of(
                            "message", "course id not found"
                    )
            );
        }
        return ResponseEntity.ok(true);
    }
}