package com.iwaconsolti.school.controller;

import com.iwaconsolti.school.controller.response.GradeRequest;
import com.iwaconsolti.school.controller.response.CourseRequest;
import com.iwaconsolti.school.controller.response.StudentRequest;
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
    public List<Grade> findGradesByStudent(@PathVariable String schoolName, @PathVariable int studentId) {
        return getServiceBySchoolName(schoolName).findGradesByStudent(studentId);
    }

    @PostMapping
    public ResponseEntity<Grade> createGrade(@PathVariable String schoolName, @RequestBody GradeRequest gradeRequest) {
        Grade grade = convertToGradeResponse(gradeRequest);

        boolean studentExists = getServiceBySchoolName(schoolName).findGradesByStudent(grade.getStudent().getId()) != null;
        boolean courseExists = getServiceBySchoolName(schoolName)
                .findCourses()
                .stream()
                .anyMatch(course -> course.getId() == grade.getCourse().getId());
        if (!studentExists || !courseExists) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }else {
            Grade createdGrade = getServiceBySchoolName(schoolName).createGrade(grade);

            if (createdGrade != null) {
                return ResponseEntity.status(HttpStatus.CREATED).body(grade);
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }
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

    private GradeRequest convertToGradeRequest(Grade grade) {
        StudentRequest studentRequest = new StudentRequest(grade.getStudent().getId(),
                grade.getStudent().getFirstName(),
                grade.getStudent().getLastName(),
                grade.getStudent().getAge());

        CourseRequest courseRequest = new CourseRequest(grade.getCourse().getId(),
                grade.getCourse().getName(),
                grade.getCourse().getProfessorName());

        return new GradeRequest(grade.getId(), grade.getScore(), studentRequest, courseRequest);
    }

    private Grade convertToGradeResponse(GradeRequest gradeRequest) {
        Student student = new Student(
                gradeRequest.getStudentRequest().getId(),
                gradeRequest.getStudentRequest().getFirstName(),
                gradeRequest.getStudentRequest().getLastName(),
                gradeRequest.getStudentRequest().getAge()
        );

        Course course = new Course(
                gradeRequest.getCourseRequest().getId(),
                gradeRequest.getCourseRequest().getName(),
                gradeRequest.getCourseRequest().getProfessorName()
        );

        return new Grade(
                gradeRequest.getId(),
                gradeRequest.getScore(),
                student,
                course
        );
    }
}
