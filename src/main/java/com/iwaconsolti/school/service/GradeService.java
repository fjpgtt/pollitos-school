package com.iwaconsolti.school.service;

import com.iwaconsolti.school.model.Grade;
import org.springframework.stereotype.Service;

import java.util.List;

public interface GradeService {

    public List<Grade> getGradesForStudent(int studentId);

    public void deleteGradesForStudent(int studentId);

    public void deleteGradesForCourse(int courseId);

    public Grade createGrade(Grade grade);
}
