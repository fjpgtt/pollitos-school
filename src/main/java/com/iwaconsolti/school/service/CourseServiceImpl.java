package com.iwaconsolti.school.service;

import com.iwaconsolti.school.model.Course;
import com.iwaconsolti.school.model.Student;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Profile("populated")
@Service
public class CourseServiceImpl implements CourseService{

    private static final Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);

    @PostConstruct
    public void init() {
        logger.info("CourseServiceImpl has initialized");
    }

    List<Course> courses = new ArrayList<>(Arrays.asList(
            new Course(1,"Math","Tere"),
            new Course(2,"Spanish","Frank"),
            new Course(3,"English","Vero"),
            new Course(4,"Programming","Gabriela")
    ));

    @Override
    public List<Course> getCurses() {
        return courses;
    }

    @Override
    public Course getCurseById(Integer id) {
        for (Course s : courses) {
            if (Objects.equals(s.getId(), id)) {
                return s;
            }
        }
        logger.error("Course not found with ID: {}", id);
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Course not found with ID: " + id);

    }

    @Override
    public void getNameCourse() {

    }

    @Override
    public void getProfessorName() {

    }

    @Override
    public void setCurseId(Integer idCourse) {

    }

    @Override
    public void setNameCourse(String nameCourse) {

    }

    @Override
    public void setProfessorName(String professorName) {

    }

    @Override
    public void addCurseId(Integer idCourse) {

    }

    @Override
    public void addNameCourse(String nameCourse) {

    }

    @Override
    public void addProfessorName(String professorName) {

    }

    @Override
    public void deleteCurseId(Integer idCourse) {

    }

    @Override
    public void deleteNameCourse(String nameCourse) {

    }

    @Override
    public void deleteProfessorName(String professorName) {

    }
}
