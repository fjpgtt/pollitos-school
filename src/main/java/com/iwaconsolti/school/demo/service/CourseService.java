package com.iwaconsolti.school.demo.service;

import com.iwaconsolti.school.demo.model.Course;
import com.iwaconsolti.school.demo.model.Student;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class CourseService {

    private final List<Course> courses = new ArrayList<>();

    public CourseService() {
        Course matematicas = Course.builder().id(1).name("Matematicas").professorName("Sergio").creationDate(new Date()).build();
        Course español = Course.builder().id(2).name("Español").professorName("Guadalupe").creationDate(new Date()).build();

        this.courses.add(matematicas);
        this.courses.add(español);
    }

    public List<Course> getCourses() {
        return this.courses;
    }

    public Course getById(int id) {
        log.info("Id: {}", id);
        List<Course> courses = this.courses.stream().filter(course -> course.getId() == id).toList();
        log.info("Students: {}", courses);
        if(!courses.isEmpty())
            return courses.getFirst();
        return null;
    }

    public Course createCourse(Course course){
        if(course != null){
            course.setCreationDate(new Date());
            this.courses.add(course);
        }
        return course;
    }

    public Course update(Course course){
        this.courses.stream().forEach(c -> {
          if (c.getId() == course.getId()){
            c.setName(course.getName());
            c.setProfessorName(course.getProfessorName());
          }
        });
        return this.courses.stream().filter(c -> c.getId() == course.getId()).toList().getFirst();
    }

}
