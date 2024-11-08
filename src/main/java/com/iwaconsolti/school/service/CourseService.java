package com.iwaconsolti.school.service;

public interface CourseService {
    public void getCurses();
    public void getCurseById();
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
