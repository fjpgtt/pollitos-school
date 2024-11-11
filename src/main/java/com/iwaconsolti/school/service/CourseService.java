package com.iwaconsolti.school.service;

import com.iwaconsolti.school.model.Course;

import java.util.List;

public interface CourseService {
    public List<Course> getCurses();

    public Course getCurseById(Integer idCourse);

    public void addCurse(Course course);

    public Course editCourse(Course course);

    public void deleteCurseId(Integer idCourse);

    public void deleteAllCurses();

}
