package com.iwaconsolti.school.service;

import com.iwaconsolti.school.model.Course;
import com.iwaconsolti.school.model.Grade;
import com.iwaconsolti.school.model.Student;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Profile("populated")
@Service
public class GradeServiceImpl implements GradeService {

    @Autowired
    private StudentService studentService;

    @Autowired
    private CourseService courseService;

    private List<Grade> grades;


    @Override
    public List<Grade> getGradesByStudent(int studentId) {
        return grades.stream()
                .filter(grade -> Objects.equals(grade.getStudent().getId(), studentId))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteAllGradesOfStudent(int studentId) {
        grades.removeIf(grade -> (Objects.equals(grade.getStudent().getId(), studentId)));
    }

    @Override
    public void deleteAllGradesOfCourse(int courseId) {
        grades.removeIf(grade -> (Objects.equals(grade.getStudent().getId(), courseId)));
    }
}
