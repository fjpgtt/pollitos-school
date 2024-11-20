package com.iwaconsolti.school.miguel.config;

import com.iwaconsolti.school.miguel.persistence.model.Courses;
import com.iwaconsolti.school.miguel.persistence.model.School;
import com.iwaconsolti.school.miguel.persistence.model.Students;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Slf4j
@Profile("populated")
public class SchoolPopulatedConfig {

    @Bean
    @Qualifier("GerardoInstitute")
    public School gerardoInstitutePopulated(){
        log.info("The populated profile is activated, the data will be loaded");

        log.info("The data was generated for the GerardoInstitute school");
        School school = new School();
        school.setName("GerardoInstitute");

        Students firstStudent = new Students();
        firstStudent.setId(1);
        firstStudent.setFirstName("Miguel");
        firstStudent.setLastName("Garcia");
        firstStudent.setAge(34);

        Students secondStudent = new Students();
        secondStudent.setId(2);
        secondStudent.setFirstName("Angelica");
        secondStudent.setLastName("Lima");
        secondStudent.setAge(56);

        //school.getStudents().put(firstStudent.getId(),firstStudent);
        //school.getStudents().put(secondStudent.getId(),secondStudent);

        Courses firstCourse = new Courses();
        firstCourse.setId(1);
        firstCourse.setNameCourse("Spring-boot");
        firstCourse.setProfessorName("Francisco");

        Courses secondCourse = new Courses();
        secondCourse.setId(2);
        secondCourse.setNameCourse("JAVA");
        secondCourse.setProfessorName("Gerardo");

        //school.getCourses().put(firstCourse.getId(), firstCourse);
        //school.getCourses().put(secondCourse.getId(), secondCourse);

        return school;
    }

    @Bean
    @Qualifier("ZetCollege")
    public School zetCollegePopulated(){
        log.info("The data was generated for the zetCollege school");

        School school = new School();
        school.setName("ZetCollege");

        Students firstStudent = new Students();
        firstStudent.setId(1);
        firstStudent.setFirstName("Juan");
        firstStudent.setLastName("Lima");
        firstStudent.setAge(34);

        Students secondStudent = new Students();
        secondStudent.setId(2);
        secondStudent.setFirstName("Ignacio");
        secondStudent.setLastName("Garcia");
        secondStudent.setAge(62);

        //school.getStudents().put(firstStudent.getId(),firstStudent);
        //school.getStudents().put(secondStudent.getId(),secondStudent);

        Courses firstCourse = new Courses();
        firstCourse.setId(1);
        firstCourse.setNameCourse("Spring-boot");
        firstCourse.setProfessorName("Francisco");

        Courses secondCourse = new Courses();
        secondCourse.setId(2);
        secondCourse.setNameCourse("JAVA");
        secondCourse.setProfessorName("Gerardo");

        //school.getCourses().put(firstCourse.getId(), firstCourse);
        //school.getCourses().put(secondCourse.getId(), secondCourse);

        log.info("I finished loading the data");

        return school;
    }
}