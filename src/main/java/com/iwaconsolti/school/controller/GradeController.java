package com.iwaconsolti.school.controller;

import com.iwaconsolti.school.controller.request.GradeRequest;
import com.iwaconsolti.school.controller.response.CourseResponse;
import com.iwaconsolti.school.controller.response.GradeResponse;
import com.iwaconsolti.school.controller.response.StudentResponse;
import com.iwaconsolti.school.model.Course;
import com.iwaconsolti.school.model.Grade;
import com.iwaconsolti.school.model.School;
import com.iwaconsolti.school.model.Student;
import com.iwaconsolti.school.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/{schoolName}/grade")
public class GradeController {

    private final GradeService gradeService;
    private final StudentService studentService;
    private final CourseService courseService;
    private final SchoolHelperService schoolHelperService;

    @Autowired
    public GradeController(GradeService gradeService, StudentService studentService, CourseService courseService, SchoolHelperService schoolHelperService) {
        this.gradeService = gradeService;
        this.studentService = studentService;
        this.courseService = courseService;
        this.schoolHelperService = schoolHelperService;
    }

    public Grade convertRequestToGrade(GradeRequest gradeRequest, School school) {

        Student student = studentService.getStudentById(gradeRequest.getStudentId(), school.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));

        Course course = courseService.getCourseById(gradeRequest.getCourseId(), school.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));

        return new Grade(
                gradeRequest.getScore(),
                student,
                course,
                school
        );
    }

    public GradeResponse convertGradeToResponse(Grade grade) {
        StudentResponse studentResponse = new StudentResponse(
                grade.getStudent().getId(),
                grade.getStudent().getFirstName(),
                grade.getStudent().getLastName(),
                grade.getStudent().getAge()
        );

        CourseResponse courseResponse = new CourseResponse(
                grade.getCourse().getId(),
                grade.getCourse().getName(),
                grade.getCourse().getProfessorName()
        );

        return new GradeResponse(
                grade.getId(),
                grade.getScore(),
                studentResponse,
                courseResponse
        );
    }


    @PostMapping
    public ResponseEntity<GradeResponse> createGrade(
            @PathVariable String schoolName,
            @RequestBody GradeRequest gradeRequest) {

        School school = schoolHelperService.findSchool(schoolName);
        Grade grade = convertRequestToGrade(gradeRequest, school);
        Grade savedGrade = gradeService.createGrade(grade);
        GradeResponse gradeResponse = convertGradeToResponse(savedGrade);

        return new ResponseEntity<>(gradeResponse, HttpStatus.CREATED);
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<GradeResponse>> getAllGradesByStudent(
            @PathVariable String schoolName,
            @PathVariable int studentId) {

        School school = schoolHelperService.findSchool(schoolName);
        List<Grade> grades = gradeService.getAllGradesByStudent(studentId, school.getId());
        List<GradeResponse> gradeResponses = grades.stream()
                .map(this::convertGradeToResponse)
                .toList();

        return new ResponseEntity<>(gradeResponses, HttpStatus.OK);
    }

    @DeleteMapping("/student/{studentId}")
    public ResponseEntity<Void> deleteAllGradesByStudent(
            @PathVariable String schoolName,
            @PathVariable int studentId) {

        School school = schoolHelperService.findSchool(schoolName);
        gradeService.deleteAllGradesByStudent(studentId, school.getId());

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/course/{courseId}")
    public ResponseEntity<Void> deleteAllGradesByCourse(
            @PathVariable String schoolName,
            @PathVariable int courseId) {

        School school = schoolHelperService.findSchool(schoolName);
        gradeService.deleteAllGradesByCourse(courseId, school.getId());

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
