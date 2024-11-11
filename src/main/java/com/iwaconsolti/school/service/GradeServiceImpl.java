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

    private static final Logger logger = LoggerFactory.getLogger(GradeServiceImpl.class);

    @Autowired
    private StudentService studentService;

    @Autowired
    private CourseService courseService;

    private List<Grade> grades;

    @PostConstruct
    public void init() {
        grades = new ArrayList<>();

        List<Student> students = studentService.getStudents();
        List<Course> courses = courseService.getAllCourses();

        grades.add(new Grade(1, 100, students.get(0), courses.get(0)));
        grades.add(new Grade(2, 100, students.get(0), courses.get(1)));
        grades.add(new Grade(3, 100, students.get(0), courses.get(2)));
        grades.add(new Grade(4, 100, students.get(0), courses.get(3)));
        grades.add(new Grade(5, 100, students.get(1), courses.get(0)));
        grades.add(new Grade(6, 100, students.get(1), courses.get(1)));
        grades.add(new Grade(7, 100, students.get(1), courses.get(2)));
        grades.add(new Grade(8, 100, students.get(1), courses.get(3)));
        grades.add(new Grade(9, 100, students.get(2), courses.get(0)));
        grades.add(new Grade(10, 100, students.get(2), courses.get(1)));
        grades.add(new Grade(11, 100, students.get(2), courses.get(2)));
        grades.add(new Grade(12, 100, students.get(2), courses.get(3)));
        grades.add(new Grade(13, 100, students.get(3), courses.get(0)));
        grades.add(new Grade(14, 100, students.get(3), courses.get(1)));
        grades.add(new Grade(15, 100, students.get(3), courses.get(2)));
        grades.add(new Grade(16, 100, students.get(3), courses.get(3)));

        logger.info("GradeServiceImpl has initialized.");
    }

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
