package com.iwaconsolti.school.service;

import com.iwaconsolti.school.model.Course;

import com.iwaconsolti.school.model.School;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;

@Service
public class CourseServiceImpl implements CourseService {

    @Override
    public List<Course> getCourses(School school) {
        return school.getCourses();
    }

    public void addCourse(School school, Course course) {
        school.getCourses().add(course);
    }

    @Override
    public Course editCourse(School school, int courseId, Course course) {
        for (Course c : school.getCourses()) {
            if (Objects.equals(c.getId(), courseId)) {
                if (course.getName() != null) {
                    c.setName(course.getName());
                }
                if (course.getProfessorName() != null) {
                    c.setProfessorName(course.getProfessorName());
                }
                return c;
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Course not found with ID: " + courseId);
    }

}
