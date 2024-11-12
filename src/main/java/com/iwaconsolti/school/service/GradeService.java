package com.iwaconsolti.school.service;

import com.iwaconsolti.school.model.Course;
import com.iwaconsolti.school.model.Grade;
import com.iwaconsolti.school.model.Student;

import java.util.List;

public interface GradeService {

    List<Grade> getGradesByStudent(int idStudent);

    void deleteAllGradesOfStudent(int idStudent);

    void deleteAllGradesOfCourse(int idCourse);
}
