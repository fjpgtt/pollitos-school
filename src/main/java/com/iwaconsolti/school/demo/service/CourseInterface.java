package com.iwaconsolti.school.demo.service;

import com.iwaconsolti.school.demo.entity.Course;

import java.util.List;

public interface CourseInterface {
    List<Course> findAllCourses();
}
