package com.iwaconsolti.school.service;

import com.iwaconsolti.school.model.Course;

import com.iwaconsolti.school.model.School;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class CourseServiceImpl implements CourseService {

    @Override
    public List<Course> findCourses(School school) {
        return school.getCourses();
    }

    @Override
    public boolean updateCourse(School school, int courseId, Course course) {
        for (Course c : school.getCourses()) {
            if (Objects.equals(c.getId(), courseId)) {
                c.setName(course.getName());
                c.setProfessorName(course.getProfessorName());
                return true;
            }
        }
        return false;
    }

}
