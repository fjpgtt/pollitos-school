package com.iwaconsolti.school.service;

import com.iwaconsolti.school.model.Course;
import com.iwaconsolti.school.model.Grade;
import com.iwaconsolti.school.model.School;
import com.iwaconsolti.school.model.Student;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class SchoolServiceImpl implements SchoolService {
    private final School school;

    private final int limitGrade;

    public SchoolServiceImpl(School school, int limitGrade) {
        this.school = school;
        this.limitGrade = limitGrade;
    }

    @Autowired
    private CourseService course;
    @Autowired
    private GradeService grade;
    @Autowired
    private StudentService student;

    @Override
    public Course createCourse(Course course) {
        school.getCourses().add(course);
        return course;
    }

    @Override
    public Student createStudent(Student newStudent) {
        school.getStudents().add(newStudent);
        return newStudent;
    }

    @Override
    public Grade createGrade(Grade grade) {
        if (grade.getScore() <= limitGrade) {
            school.getGrades().add(grade);
            return grade;
        } else {
            return null;
        }
    }

    @Override
    public List<Student> findStudents() {
        return student.findStudents(school);
    }

    @Override
    public Student updateStudent(int id, Student updatedStudent) {
        return student.updateStudent(school, id, updatedStudent);
    }

    @Override
    public List<Course> findCourses() {
        return course.findCourses(school);
    }

    @Override
    public boolean updateCourse(int id, Course updatedCourse) {
        return course.updateCourse(school, id, updatedCourse);
    }

    @Override
    public List<Grade> findGradesByStudent(int studentId) {
        return grade.findGradesByStudent(school, studentId);
    }

    @Override
    public void deleteGradesOfStudent(int studentId) {
        grade.deleteGradesOfStudent(school,studentId);
    }

    @Override
    public void deleteGradesOfCourse(int courseId) {
        grade.deleteGradesOfCourse(school, courseId);
    }
}
