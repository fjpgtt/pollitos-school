package com.iwaconsolti.school.demo.Config;

import com.iwaconsolti.school.demo.model.*;
import com.iwaconsolti.school.demo.repository.CourseRepository;
import com.iwaconsolti.school.demo.repository.GradeRepository;
import com.iwaconsolti.school.demo.repository.SchoolRepository;
import com.iwaconsolti.school.demo.repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
    CommandLineRunner initDatabase( SchoolRepository schoolRepository, StudentRepository studentRepository, CourseRepository courseRepository,  GradeRepository gradeRepository) {
        return args -> {
            log.info("Populating data for GerardoInstitute and ZetCollege (APPCONFIG)");

            School gerardoInstitute = schoolRepository.save(new School("GerardoInstitute"));
            School zetCollege = schoolRepository.save(new School("ZetCollege"));

            Student juan = studentRepository.save(new Student("Juan", "Perez", 18, gerardoInstitute.getId()));
            Student ana = studentRepository.save(new Student("Ana", "Lopez", 22, gerardoInstitute.getId()));
            Student mario = studentRepository.save(new Student("Mario", "Hdz", 22, zetCollege.getId()));
            Student ernesto = studentRepository.save(new Student("Ernesto", "Gzlz", 25, zetCollege.getId()));

            Course mathematics = courseRepository.save(new Course("Mathematics", "Dr. Smith", gerardoInstitute.getId()));
            Course history = courseRepository.save(new Course("History", "Dr. Brown", gerardoInstitute.getId()));
            Course civic = courseRepository.save(new Course("Civic", "Dr. XX", gerardoInstitute.getId()));
            Course geography = courseRepository.save(new Course("Geography", "Dr. ZZ", gerardoInstitute.getId()));
            Course psychology = courseRepository.save(new Course("Psychology", "Dr. MM", zetCollege.getId()));

            gradeRepository.save(new Grade(juan.getId(), mathematics.getId(), 95));
            gradeRepository.save(new Grade(juan.getId(), history.getId(), 90));
            gradeRepository.save(new Grade(juan.getId(), civic.getId(), 80));
            gradeRepository.save(new Grade(ana.getId(), history.getId(), 85));
            gradeRepository.save(new Grade(ana.getId(), mathematics.getId(), 100));
            gradeRepository.save(new Grade(mario.getId(), mathematics.getId(), 100));

        };
    }
}
