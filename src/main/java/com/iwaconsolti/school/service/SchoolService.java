package com.iwaconsolti.school.service;

import com.iwaconsolti.school.model.Course;
import com.iwaconsolti.school.model.Grade;
import com.iwaconsolti.school.model.Student;

import java.util.List;

public interface SchoolService {

    List<Student> getStudents();

    void editStudent(int id, Student updatedStudent);

    List<Course> getCourses();

    void editCourse(int id, Course updatedCourse);

    List<Grade> getGradesByStudent(int studentId);

    void deleteGradesOfStudent(int studentId);

    void deleteGradesOfCourse(int courseId);
}
