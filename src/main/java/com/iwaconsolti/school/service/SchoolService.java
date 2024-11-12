package com.iwaconsolti.school.service;

import com.iwaconsolti.school.model.Course;
import com.iwaconsolti.school.model.Grade;
import com.iwaconsolti.school.model.Student;

import java.util.List;

public interface SchoolService {
    void addStudent(Student student);

    List<Student> getAllStudents();

    void editStudent(int id, Student updatedStudent);

    void addCourse(Course course);

    List<Course> getAllCourses();

    void editCourse(int id, Course updatedCourse);

    void addGrade(Grade grade);

    List<Grade> getGradesByStudent(int studentId);

    void deleteGradesByStudent(int studentId);

    void deleteGradesByCourse(int courseId);
}
