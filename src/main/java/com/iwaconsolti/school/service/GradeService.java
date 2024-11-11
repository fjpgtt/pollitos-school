package com.iwaconsolti.school.service;

import com.iwaconsolti.school.model.Course;
import com.iwaconsolti.school.model.Grade;
import com.iwaconsolti.school.model.Student;

import java.util.List;

public interface GradeService {
    public List<Grade> getAllGrades();

    public List<Grade> getGradesByStudent(Integer idStudent);

    public Grade addGrade(Grade grade);

    public Grade editGrade(Grade grade);

    public void deleteAllGradesOfStudent(Integer idStudent);

    public void deleteAllGradesOfCourse(Integer idCourse);
}
