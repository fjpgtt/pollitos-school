package com.iwaconsolti.school.demo.Config;

import com.iwaconsolti.school.demo.model.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Slf4j
@Configuration
public class AppConfig {

    @Bean(name = "gerardoInstitute")
    public School gerardoInstitute() {
        return new School("GerardoInstitute");
    }

    @Bean(name = "zetCollege")
    public School zetCollege() {
        return new School("ZetCollege");
    }

    @Bean
    public Student student() {return new Student();}

    @Bean
    public Course course() {return new Course();}

    @Bean
    public Grade grade() {return new Grade();}

    @Bean
    @Profile("populated")
    public String populateData(School gerardoInstitute, School zetCollege) {
        log.info("Populating data for GerardoInstitute and ZetCollege (APPCONFIG)");

        gerardoInstitute.getGradeList().add(new Grade(100, 1, 1));
        gerardoInstitute.getGradeList().add(new Grade(90, 2, 1));
        gerardoInstitute.getGradeList().add(new Grade(50, 3, 1));
        gerardoInstitute.getGradeList().add(new Grade(90, 1, 2));

        zetCollege.getGradeList().add(new Grade(80, 3, 3));
        zetCollege.getGradeList().add(new Grade(100, 3, 3));
        zetCollege.getGradeList().add(new Grade(60, 3, 3));
        zetCollege.getGradeList().add(new Grade(70, 3, 4));

        gerardoInstitute.getCourseList().add(new Course(1, "Matemáticas", "Prof. González"));
        gerardoInstitute.getCourseList().add(new Course(2, "Historia", "Prof. Sánchez"));

        zetCollege.getCourseList().add(new Course(3, "Ciencias", "Prof. Ramírez"));
        zetCollege.getCourseList().add(new Course(4, "Filosofía", "Prof. García"));

        gerardoInstitute.getStudentsList().add(new Student(1, "Juan", "Perez", 18));
        gerardoInstitute.getStudentsList().add(new Student(2, "Pedro", "Gonzales", 20));

        zetCollege.getStudentsList().add(new Student(3, "Ana", "Lopez", 22));
        zetCollege.getStudentsList().add(new Student(4, "Luis", "Martínez", 25));
        return "Populate Data";
    }
}
