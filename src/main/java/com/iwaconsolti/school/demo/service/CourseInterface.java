package com.iwaconsolti.school.demo.service;

import com.iwaconsolti.school.demo.entity.CourseEntity;

import java.util.List;

public interface CourseInterface {
    List<CourseEntity> findAllCourses();
}
