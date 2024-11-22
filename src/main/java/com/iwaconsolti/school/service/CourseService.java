package com.iwaconsolti.school.service;

import com.iwaconsolti.school.model.Course;
import java.util.List;
import java.util.Optional;

public interface CourseService {

    public List<Course> getAllCourses();

    public Optional<Course> getCourseById(int id, int schoolId);

    public Course createCourse(Course course);

    public Course updateCourse(int id, Course course);

    public void deleteCourse(int id);
}
