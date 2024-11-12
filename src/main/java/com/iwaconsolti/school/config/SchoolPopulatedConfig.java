package com.iwaconsolti.school.config;

import com.iwaconsolti.school.model.Course;
import com.iwaconsolti.school.model.Grade;
import com.iwaconsolti.school.model.Schools.GerardoInstitute;
import com.iwaconsolti.school.model.School;
import com.iwaconsolti.school.model.Schools.ZetCollege;
import com.iwaconsolti.school.model.Student;
import com.iwaconsolti.school.service.SchoolService;
import com.iwaconsolti.school.service.SchoolServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("populated")
@Configuration
public class SchoolPopulatedConfig {
    @Bean
    public School gerardoInstitute() {
        return new GerardoInstitute();
    }

    @Bean
    public School zetCollege() {
        return new ZetCollege();
    }

    @Bean("gerardoService")
    public SchoolService gerardoServicePopulated() {
        School gerardoInstitute = gerardoInstitute();
        SchoolServiceImpl schoolService = new SchoolServiceImpl(gerardoInstitute);

        Student student1 = new Student(1, "Juan", "Perez", 20);
        Student student2 = new Student(2, "Ana", "Gomez", 22);

        schoolService.addStudent(student1);
        schoolService.addStudent(student2);

        Course course1 = new Course(1, "Matemáticas", "Prof. Ramirez");
        Course course2 = new Course(2, "Historia", "Prof. López");

        schoolService.addCourse(course1);
        schoolService.addCourse(course2);

        Grade grade1 = new Grade(1, 85, student1, course1);
        Grade grade2 = new Grade(2, 90, student1, course2);
        Grade grade3 = new Grade(3, 78, student2, course1);
        Grade grade4 = new Grade(4, 88, student2, course2);

        schoolService.addGrade(grade1);
        schoolService.addGrade(grade2);
        schoolService.addGrade(grade3);
        schoolService.addGrade(grade4);

        return schoolService;
    }

    @Bean("zetService")
    public SchoolService zetServicePopulated() {
        School zetCollege = zetCollege();
        SchoolServiceImpl schoolService = new SchoolServiceImpl(zetCollege);

        Student student1 = new Student(1, "Carlos", "Martinez", 21);
        Student student2 = new Student(2, "Lucia", "Fernandez", 23);

        schoolService.addStudent(student1);
        schoolService.addStudent(student2);

        Course course1 = new Course(3, "Física", "Prof. García");
        Course course2 = new Course(4, "Química", "Prof. Sánchez");

        schoolService.addCourse(course1);
        schoolService.addCourse(course2);

        Grade grade1 = new Grade(1, 92, student1, course1);
        Grade grade2 = new Grade(2, 87, student1, course2);
        Grade grade3 = new Grade(3, 75, student2, course1);
        Grade grade4 = new Grade(4, 80, student2, course2);

        schoolService.addGrade(grade1);
        schoolService.addGrade(grade2);
        schoolService.addGrade(grade3);
        schoolService.addGrade(grade4);

        return schoolService;
    }
}
