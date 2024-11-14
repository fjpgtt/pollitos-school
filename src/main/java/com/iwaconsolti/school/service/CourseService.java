package com.iwaconsolti.school.service;

import com.iwaconsolti.school.model.Course;
import com.iwaconsolti.school.model.School;

import java.util.List;

public interface CourseService {
    List<Course> findCourses(School school);

    Course updateCourse(School school, int courseId, Course course);

}
