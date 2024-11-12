package com.iwaconsolti.school.service;

import com.iwaconsolti.school.model.Course;
import com.iwaconsolti.school.model.Grade;
import com.iwaconsolti.school.model.School;
import com.iwaconsolti.school.model.Student;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


public class SchoolServiceImpl implements SchoolService {
    private final School school;

    @Autowired
    private CourseService course;
    @Autowired
    private GradeService grade;
    @Autowired
    private StudentService student;

    public SchoolServiceImpl(School school) {
        this.school = school;
    }

    public void addCourse(Course course) {
        school.getCourses().add(course);
    }

    public void addStudent(Student student) {
        school.getStudents().add(student);
    }

    public void addGrade(Grade grade) {
        school.getGrades().add(grade);
    }

    @Override
    public List<Student> getStudents() {
        return student.getStudents(school);
    }

    @Override
    public void editStudent(int id, Student updatedStudent) {
        student.editStudent(school, id, updatedStudent);
    }

    @Override
    public List<Course> getCourses() {
        return course.getCourses(school);
    }

    @Override
    public void editCourse(int id, Course updatedCourse) {
        course.editCourse(school, id, updatedCourse);
    }

    @Override
    public List<Grade> getGradesByStudent(int studentId) {
        return grade.getGradesByStudent(school, studentId);
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
