package com.iwaconsolti.school.miguel.config;

import com.iwaconsolti.school.miguel.model.Courses;
import com.iwaconsolti.school.miguel.model.School;
import com.iwaconsolti.school.miguel.model.Students;
import com.iwaconsolti.school.miguel.model.dto.StudentsDTO;
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
        School school = new School("GerardoInstitute");

        StudentsDTO firstStudentDTO = new StudentsDTO();
        firstStudentDTO.setId(1);
        firstStudentDTO.setFirstName("Miguel");
        firstStudentDTO.setLastName("Garcia");
        firstStudentDTO.setAge(34);

        StudentsDTO secondStudentDTO = new StudentsDTO();
        secondStudentDTO.setId(2);
        secondStudentDTO.setFirstName("Angelica");
        secondStudentDTO.setLastName("Lima");
        secondStudentDTO.setAge(56);

        Students firstStudent = new Students(firstStudentDTO);
        Students secondStudent = new Students(secondStudentDTO);

        school.getStudents().put(firstStudent.getId(),firstStudent);
        school.getStudents().put(secondStudent.getId(),secondStudent);

        Courses firstCourse = new Courses();
        firstCourse.setId(1);
        firstCourse.setNameCourse("Spring-boot");
        firstCourse.setProfessorName("Francisco");

        Courses secondCourse = new Courses();
        secondCourse.setId(2);
        secondCourse.setNameCourse("JAVA");
        secondCourse.setProfessorName("Gerardo");

        school.getCourses().put(firstCourse.getId(), firstCourse);
        school.getCourses().put(secondCourse.getId(), secondCourse);

        return school;
    }

    @Bean
    @Qualifier("ZetCollege")
    public School zetCollegePopulated(){

        School school = new School("ZetCollege");

        StudentsDTO firstStudentDTO = new StudentsDTO();
        firstStudentDTO.setId(1);
        firstStudentDTO.setFirstName("Juan");
        firstStudentDTO.setLastName("Lima");
        firstStudentDTO.setAge(34);

        StudentsDTO secondStudentDTO = new StudentsDTO();
        secondStudentDTO.setId(2);
        secondStudentDTO.setFirstName("Ignacio");
        secondStudentDTO.setLastName("Garcia");
        secondStudentDTO.setAge(62);

        Students firstStudent = new Students(firstStudentDTO);
        Students secondStudent = new Students(secondStudentDTO);

        school.getStudents().put(firstStudent.getId(),firstStudent);
        school.getStudents().put(secondStudent.getId(),secondStudent);

        Courses firstCourse = new Courses();
        firstCourse.setId(1);
        firstCourse.setNameCourse("Spring-boot");
        firstCourse.setProfessorName("Francisco");

        Courses secondCourse = new Courses();
        secondCourse.setId(2);
        secondCourse.setNameCourse("JAVA");
        secondCourse.setProfessorName("Gerardo");

        school.getCourses().put(firstCourse.getId(), firstCourse);
        school.getCourses().put(secondCourse.getId(), secondCourse);
        return school;
    }
}