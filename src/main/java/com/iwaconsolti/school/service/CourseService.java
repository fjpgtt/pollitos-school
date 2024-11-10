package com.iwaconsolti.school.service;

import com.iwaconsolti.school.model.Course;

import java.util.List;

public interface CourseService {
    public List<Course> getCurses();
    public Course getCurseById(Integer idCourse);
    public String getNameCourse(Integer idCourse);
    public String getProfessorName(Integer idCourse);

    public void addCurse(Course course);

    public void setNameCourse(Course course);
    public void setProfessorName(Course course);

    public void deleteCurseId(Integer idCourse);
    public void deleteAllCurses();

}
