package com.iwaconsolti.school.service;

import com.iwaconsolti.school.controller.request.CourseRequest;
import com.iwaconsolti.school.model.Course;
import java.util.List;
import java.util.Optional;

public interface CourseService {

    List<Course> findAllBySchoolId(int schoolId);

    Optional<Course> getCourseById(int id, int schoolId);

    Course createCourse(Course course);

    Course updateCourse(int id, CourseRequest courseRequest, int schoolId);

}
