package com.iwaconsolti.school.service;

import com.iwaconsolti.school.model.Course;
import com.iwaconsolti.school.model.Grade;
import com.iwaconsolti.school.model.Student;

import java.util.List;

public interface SchoolService {

    List<Student> findStudents();

    Student createStudent(Student student);

    Student updateStudent(int id, Student updatedStudent);

    List<Course> findCourses();

    Course createCourse(Course course);

    boolean updateCourse(int id, Course updatedCourse);

    List<Grade> findGradesByStudent(int studentId);

    Grade createGrade(Grade grade);

    void deleteGradesOfStudent(int studentId);

    void deleteGradesOfCourse(int courseId);
}
