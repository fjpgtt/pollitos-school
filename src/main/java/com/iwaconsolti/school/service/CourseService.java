package com.iwaconsolti.school.service;

import com.iwaconsolti.school.model.Course;

import java.util.List;

public interface CourseService {
    public List<Course> getCurses();
    public Course getCurseById(Integer id);
    public void getNameCourse();
    public void getProfessorName();

    public void setCurseId(Integer idCourse);
    public void setNameCourse(String nameCourse);
    public void setProfessorName(String professorName);

    public void addCurseId(Integer idCourse);
    public void addNameCourse(String nameCourse);
    public void addProfessorName(String professorName);

    public void deleteCurseId(Integer idCourse);
    public void deleteNameCourse(String nameCourse);
    public void deleteProfessorName(String professorName);
}
