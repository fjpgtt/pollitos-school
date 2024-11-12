package com.iwaconsolti.school.service;

import com.iwaconsolti.school.model.Grade;
import com.iwaconsolti.school.model.School;

import java.util.List;

public interface GradeService {

    List<Grade> getGradesByStudent(School school, int idStudent);

    void deleteGradesOfStudent(School school, int idStudent);

    void deleteGradesOfCourse(School school, int idCourse);
}
