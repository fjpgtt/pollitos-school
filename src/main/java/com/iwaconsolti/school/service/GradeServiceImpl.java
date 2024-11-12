package com.iwaconsolti.school.service;

import com.iwaconsolti.school.model.Grade;
import com.iwaconsolti.school.model.School;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class GradeServiceImpl implements GradeService {

    @Autowired
    private StudentService studentService;

    @Autowired
    private CourseService courseService;

    private List<Grade> grades;


    @Override
    public List<Grade> getGradesByStudent(School school, int studentId) {
        return school.getGrades().stream()
                .filter(grade -> Objects.equals(grade.getStudent().getId(), studentId))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteGradesOfStudent(School school, int studentId) {
        school.getGrades().stream()
                .filter(grade -> Objects.equals(grade.getStudent().getId(), studentId))
                .forEach(grade -> grade.setScore(0));
    }

    @Override
    public void deleteGradesOfCourse(School school, int courseId) {
        school.getGrades().stream()
                .filter(grade -> Objects.equals(grade.getCourse().getId(), courseId))
                .forEach(grade -> grade.setScore(0));
    }

}
