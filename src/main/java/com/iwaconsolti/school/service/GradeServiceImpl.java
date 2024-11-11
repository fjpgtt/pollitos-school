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
    public List<Grade> getGradesByStudent(Integer idStudent) {
        return grades.stream()
                .filter(grade -> (grade.getStudent().getId()).equals(idStudent))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteAllGradesOfStudent(Integer studentId) {
        grades.removeIf(grade -> (grade.getStudent().getId()).equals(studentId));
    }

    @Override
    public void deleteAllGradesOfCourse(Integer courseId) {
        grades.removeIf(grade -> (grade.getCourse().getId()).equals(courseId));
    }
}
