package com.iwaconsolti.school.service;

import com.iwaconsolti.school.model.Course;
import com.iwaconsolti.school.model.Grade;
import com.iwaconsolti.school.model.School;
import com.iwaconsolti.school.model.Student;

import java.util.List;
import java.util.stream.Collectors;

public class SchoolServiceImpl implements SchoolService {
    private final School school;

    public SchoolServiceImpl(School school) {
        this.school = school;
    }

    @Override
    public void addStudent(Student student) {
        school.getStudents().add(student);
    }

    @Override
    public List<Student> getAllStudents() {
        return school.getStudents();
    }

    @Override
    public void editStudent(Integer id, Student updatedStudent) {
        // Implementación de edición de estudiante
    }

    @Override
    public void addCourse(Course course) {
        school.getCourses().add(course);
    }

    @Override
    public List<Course> getAllCourses() {
        return school.getCourses();
    }

    @Override
    public void editCourse(Integer id, Course updatedCourse) {
        // Implementación de edición de curso
    }

    @Override
    public void addGrade(Grade grade) {
        school.getGrades().add(grade);
    }

    public List<Grade> getGrades() {
        return school.getGrades();
    }

    @Override
    public List<Grade> getGradesByStudent(Integer studentId) {
        return school.getGrades().stream()
                .filter(grade -> grade.getStudent().getId().equals(studentId))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteGradesByStudent(Integer studentId) {
        school.setGrades(
                school.getGrades().stream()
                        .filter(grade -> !grade.getStudent().getId().equals(studentId))
                        .collect(Collectors.toList())
        );
    }

    @Override
    public void deleteGradesByCourse(Integer courseId) {
        school.setGrades(
                school.getGrades().stream()
                        .filter(grade -> !grade.getCourse().getId().equals(courseId))
                        .collect(Collectors.toList())
        );
    }
}
