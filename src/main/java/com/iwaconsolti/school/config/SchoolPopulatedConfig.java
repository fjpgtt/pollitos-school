package com.iwaconsolti.school.config;

import com.iwaconsolti.school.model.Course;
import com.iwaconsolti.school.model.Grade;
import com.iwaconsolti.school.model.School;
import com.iwaconsolti.school.model.Student;
import com.iwaconsolti.school.model.schools.GerardoInstitute;
import com.iwaconsolti.school.model.schools.ZetCollege;
import com.iwaconsolti.school.service.CourseService;
import com.iwaconsolti.school.service.GradeService;
import com.iwaconsolti.school.service.SchoolService;
import com.iwaconsolti.school.service.StudentService;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("populated")
@Slf4j
@Configuration
public class SchoolPopulatedConfig {

    private final SchoolService schoolService;
    private final StudentService studentService;
    private final CourseService courseService;
    private final GradeService gradeService;

    @Autowired
    public SchoolPopulatedConfig(SchoolService schoolService,
                                 StudentService studentService,
                                 CourseService courseService,
                                 GradeService gradeService) {
        this.schoolService = schoolService;
        this.studentService = studentService;
        this.courseService = courseService;
        this.gradeService = gradeService;
    }

    @PostConstruct
    public void populateData() {
        School gerardoInstitute = schoolService.createSchool(new GerardoInstitute("GerardoInstitute"));

        Student student1 = studentService.createStudent(new Student("Juan", "Perez", 20, gerardoInstitute));
        Student student2 = studentService.createStudent(new Student("Ana", "Gomez", 22, gerardoInstitute));


        Course course1 = courseService.createCourse(new Course("Matemáticas", "Prof. Ramirez", gerardoInstitute));
        Course course2 = courseService.createCourse(new Course("Historia", "Prof. López", gerardoInstitute));

        gradeService.createGrade(new Grade(85, student1, course1, gerardoInstitute));
        gradeService.createGrade(new Grade(90, student1, course2, gerardoInstitute));
        gradeService.createGrade(new Grade(78, student2, course1, gerardoInstitute));
        gradeService.createGrade(new Grade(88, student2, course2, gerardoInstitute));


        School zetCollege = schoolService.createSchool(new ZetCollege("ZetCollege"));

        Student student3 = studentService.createStudent(new Student("Carlos", "Martinez", 21, zetCollege));
        Student student4 = studentService.createStudent(new Student("Lucia", "Fernandez", 23, zetCollege));

        Course course3 = courseService.createCourse(new Course("Física", "Prof. García", zetCollege));
        Course course4 = courseService.createCourse(new Course("Química", "Prof. Sánchez", zetCollege));

        gradeService.createGrade(new Grade(92, student3, course3, zetCollege));
        gradeService.createGrade(new Grade(87, student3, course4, zetCollege));
        gradeService.createGrade(new Grade(75, student4, course3, zetCollege));
        gradeService.createGrade(new Grade(80, student4, course4, zetCollege));
    }

}
