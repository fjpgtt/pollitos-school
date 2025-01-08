package com.iwaconsolti.school.miguel.controller;

import com.iwaconsolti.school.miguel.persistence.model.Grade;
import com.iwaconsolti.school.miguel.persistence.model.School;
import com.iwaconsolti.school.miguel.persistence.model.dto.GradesDTO;
import com.iwaconsolti.school.miguel.persistence.repository.SchoolRepository;
import com.iwaconsolti.school.miguel.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/app/{nameSchool}")
public class GradeController {

    @Autowired
    private GradeService gradeService;

    @Autowired
    private SchoolRepository schoolRepository;

    @Value("${school.score.limit:100}")
    private int limitGrade;

    @PostMapping("/grade")
    public ResponseEntity<Object> createGrade(@PathVariable String nameSchool, @RequestBody GradesDTO gradesDTO){

        School school = schoolRepository.findByName(nameSchool);

        if(school == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    Map.of(
                            "message", "the school was not found",
                            "status", HttpStatus.NOT_FOUND
                    )
            );
        }

        if(gradesDTO.getScore() < 0 || gradesDTO.getScore() > limitGrade){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    Map.of(
                            "message", "The limit allowed in the score was exceeded", "status", HttpStatus.BAD_REQUEST
                    )
            );
        }

        if(gradesDTO.getScore() == 0 || gradesDTO.getCourseId() == 0 || gradesDTO.getStudentId() == 0){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    Map.of(
                            "message","Required data is missing", "status", HttpStatus.BAD_REQUEST
                    )
            );
        }
        Grade objGrade =  convertToEntity(gradesDTO);
        gradeService.createGrade(objGrade);

        return ResponseEntity.status(HttpStatus.CREATED).body(
                Map.of(
                        "message", "The Grade was successfully registered", "status", HttpStatus.CREATED,
                        "grade", objGrade
                )
        );
    }

    @GetMapping("/grade/student/{studentId}")
    public ResponseEntity<Object> getGradesByStudentId(@PathVariable String nameSchool, @PathVariable int studentId){

        School school = schoolRepository.findByName(nameSchool);

        if(school == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    Map.of(
                            "message", "the school was not found",
                            "status", HttpStatus.NOT_FOUND
                    )
            );
        }

        List<Map<String, Object>> gradeByStudentId = gradeService.findStudentById(studentId);

        if(gradeByStudentId.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    Map.of(
                         "message", "there are no grades recorded for the student", "status", HttpStatus.NOT_FOUND
                    )
            );
        }
        return ResponseEntity.ok(gradeByStudentId);
    }

    @DeleteMapping("/grade/student/{studentId}")
    public ResponseEntity<Object> deleteGradesByStudentId(@PathVariable String nameSchool,@PathVariable int studentId){

        School school = schoolRepository.findByName(nameSchool);

        if(school == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    Map.of(
                            "message", "the school was not found",
                            "status", HttpStatus.NOT_FOUND
                    )
            );
        }

        int result = gradeService.deleteGradeByStudentId(studentId);

        if(result == 0){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    Map.of(
                            "message", "no grade was found associated with the student id",
                            "status", HttpStatus.NOT_FOUND
                    )
            );
        }
        return ResponseEntity.ok(
                Map.of(
                        "message", "student's grades are successfully removed",
                        "status", HttpStatus.OK
                )
        );
    }

    @DeleteMapping("/grade/course/{courseId}")
    public ResponseEntity<Object> deleteGradesByCourseId(@PathVariable String nameSchool,@PathVariable int courseId){
        Integer result = gradeService.deleteGradeByCourseId(courseId);

        if(result == 0){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    Map.of(
                            "message", "no grade was found associated with the course id",
                            "status", HttpStatus.NOT_FOUND
                    )
            );
        }
        return ResponseEntity.ok(
                Map.of(
                        "message", "course's grades are successfully removed",
                        "status", HttpStatus.OK
                )
        );
    }

    protected GradesDTO convertToDto(Grade entity){
        return new GradesDTO(entity);
    }

    protected Grade convertToEntity(GradesDTO dto){
        Grade grade = new Grade(dto);
        if(!Objects.isNull(dto.getId())){
            grade.setId(dto.getId());
        }
        return grade;
    }
}