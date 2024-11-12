package com.iwaconsolti.school.service;

import com.iwaconsolti.school.model.Course;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
    public Course editCourse(int courseId, Course course) {
        for (Course s : courses) {
            if (Objects.equals(s.getId(), courseId)) {
                if (course.getName() != null){
                    s.setName(course.getName());
                }
                if (course.getProfessorName() != null){
                    s.setProfessorName(course.getProfessorName());
                }
                return course;
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "CourseResponse not found with ID: " + course.getId());
    }

}
