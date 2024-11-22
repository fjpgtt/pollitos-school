package com.iwaconsolti.school.service;

import com.iwaconsolti.school.model.Grade;
import org.springframework.stereotype.Service;

import java.util.List;

public interface GradeService {

    public List<Grade> getAllGradesByStudent(int studentId, int schoolId);

    public void deleteAllGradesByStudent(int studentId, int schoolId);

    public void deleteAllGradesByCourse(int courseId, int schoolId);

    public Grade createGrade(Grade grade);
}
