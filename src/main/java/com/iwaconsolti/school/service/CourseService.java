package com.iwaconsolti.school.service;

import com.iwaconsolti.school.model.Course;

import java.util.List;

public interface CourseService {
    List<Course> getAllCourses();

    Course editCourse(Course course);

}
