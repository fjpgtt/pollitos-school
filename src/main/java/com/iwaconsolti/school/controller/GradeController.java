package com.iwaconsolti.school.controller;

import com.iwaconsolti.school.controller.response.GradeResponse;
import com.iwaconsolti.school.controller.response.CourseResponse;
import com.iwaconsolti.school.controller.response.StudentResponse;
import com.iwaconsolti.school.model.Grade;
import com.iwaconsolti.school.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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

    @GetMapping("/student/{studentId}")
    public List<GradeResponse> getGradesByStudent(@PathVariable String schoolName, @PathVariable int studentId) {
        List<Grade> grades = getServiceBySchoolName(schoolName).getGradesByStudent(studentId);
        return grades.stream()
                .map(this::convertToGradeResponse)
                .collect(Collectors.toList());
    }

    @DeleteMapping("/student/{studentId}")
    public void deleteGradesByStudent(@PathVariable String schoolName, @PathVariable int studentId) {
        getServiceBySchoolName(schoolName).deleteGradesOfStudent(studentId);
    }

    @DeleteMapping("/course/{courseId}")
    public void deleteGradesByCourse(@PathVariable String schoolName, @PathVariable int courseId) {
        getServiceBySchoolName(schoolName).deleteGradesOfCourse(courseId);
    }
}
