package com.iwaconsolti.school.service;

import com.iwaconsolti.school.model.Course;
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

@Service
public class CourseServiceImpl implements CourseService {

    private static final Logger logger = LoggerFactory.getLogger(CourseServiceImpl.class);
    List<Course> courses;
    @Profile("populated")
    @PostConstruct
    public void init() {
        courses = new ArrayList<>(Arrays.asList(
                new Course(1, "Math", "Teresa"),
                new Course(2, "Spanish", "Frank"),
                new Course(3, "English", "Veronica"),
                new Course(4, "Programming", "Gabriela")
        ));
        logger.info("CourseServiceImpl has initialized.");
    }

    @Override
    public List<Course> getAllCourses() {
        return courses;
    }


    @Override
    public Course editCourse(Course course) {
        for (Course s : courses) {
            if (Objects.equals(s.getId(), course.getId())) {
                if (course.getName() != null){
                    s.setName(course.getName());
                }
                if (course.getProfessorName() != null){
                    s.setProfessorName(course.getProfessorName());
                }
                return course;
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Course not found with ID: " + course.getId());
    }

}
