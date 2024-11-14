package com.iwaconsolti.school.controller;

import com.iwaconsolti.school.controller.response.GradeResponse;
import com.iwaconsolti.school.controller.response.CourseResponse;
import com.iwaconsolti.school.controller.response.StudentResponse;
import com.iwaconsolti.school.model.Course;
import com.iwaconsolti.school.model.Grade;
import com.iwaconsolti.school.model.Student;
import com.iwaconsolti.school.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/{schoolName}/grade")
public class GradeController {

    @Autowired
    @Qualifier("gerardoService")
    private SchoolService gerardoService;

    @Autowired
    @Qualifier("zetService")
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
    public List<GradeResponse> findGradesByStudent(@PathVariable String schoolName, @PathVariable int studentId) {
        List<Grade> grades = getServiceBySchoolName(schoolName).findGradesByStudent(studentId);
        return grades.stream()
                .map(this::convertToGradeResponse)
                .collect(Collectors.toList());
    }
    @PostMapping
    public ResponseEntity<GradeResponse> createGrade(@PathVariable String schoolName, @RequestBody GradeResponse gradeResponse){
        Grade grade = convertToGrade(gradeResponse);
        if (getServiceBySchoolName(schoolName).createGrade(grade) != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(gradeResponse);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @DeleteMapping("/student/{studentId}")
    public void deleteGradesByStudent(@PathVariable String schoolName, @PathVariable int studentId) {
        getServiceBySchoolName(schoolName).deleteGradesOfStudent(studentId);
    }

    @DeleteMapping("/course/{courseId}")
    public void deleteGradesByCourse(@PathVariable String schoolName, @PathVariable int courseId) {
        getServiceBySchoolName(schoolName).deleteGradesOfCourse(courseId);
    }

    private GradeResponse convertToGradeResponse(Grade grade) {
        StudentResponse studentResponse = new StudentResponse(grade.getStudent().getId(),
                grade.getStudent().getFirstName(),
                grade.getStudent().getLastName(),
                grade.getStudent().getAge());

        CourseResponse courseResponse = new CourseResponse(grade.getCourse().getId(),
                grade.getCourse().getName(),
                grade.getCourse().getProfessorName());

        return new GradeResponse(grade.getId(), grade.getScore(), studentResponse, courseResponse);
    }

    private Grade convertToGrade(GradeResponse gradeResponse) {
        Student student = new Student(
                gradeResponse.getStudentResponse().getId(),
                gradeResponse.getStudentResponse().getFirstName(),
                gradeResponse.getStudentResponse().getLastName(),
                gradeResponse.getStudentResponse().getAge()
        );

        Course course = new Course(
                gradeResponse.getCourseResponse().getId(),
                gradeResponse.getCourseResponse().getName(),
                gradeResponse.getCourseResponse().getProfessorName()
        );

        return new Grade(
                gradeResponse.getId(),
                gradeResponse.getScore(),
                student,
                course
        );
    }
}
