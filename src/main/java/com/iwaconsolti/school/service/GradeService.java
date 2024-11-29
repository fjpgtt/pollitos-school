package com.iwaconsolti.school.service;

import com.iwaconsolti.school.model.Grade;

import java.util.List;

public interface GradeService {

    List<Grade> getAllGradesByStudent(int studentId, int schoolId);

    void deleteAllGradesByStudent(int studentId, int schoolId);

    void deleteAllGradesByCourse(int courseId, int schoolId);

    Grade createGrade(Grade grade);
}
