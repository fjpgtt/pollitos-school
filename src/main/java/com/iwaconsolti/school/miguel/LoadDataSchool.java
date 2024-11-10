package com.iwaconsolti.school.miguel;

import com.iwaconsolti.school.miguel.model.Courses;
import com.iwaconsolti.school.miguel.model.School;
import com.iwaconsolti.school.miguel.model.Students;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Slf4j
@Configuration
@Profile("populated")
public class LoadDataSchool implements CommandLineRunner {

    @Autowired
    private School gerardoInstitute;
    @Autowired
    private School zetCollege;

    @Override
    public void run(String... args) throws Exception {
        log.info("The populated profile is activated, the data will be loaded");

        log.info("The data was generated for the GerardoInstitute school");
        loadData(gerardoInstitute);

        log.info("The data was generated for the zetCollege school");
        loadData(zetCollege);

        log.info("I finished loading the data");

    }

    private void loadData(School school){

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

    }
}
