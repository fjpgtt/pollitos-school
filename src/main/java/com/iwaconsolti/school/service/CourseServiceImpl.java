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

    List<Course> courses;

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
