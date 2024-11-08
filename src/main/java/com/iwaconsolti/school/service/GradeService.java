package com.iwaconsolti.school.service;

import com.iwaconsolti.school.model.Course;
import com.iwaconsolti.school.model.Student;

public interface GradeService {
    public Student getCourseById(Integer idGrade);
    public Student getStudent();
    public Integer getScore();
    public Course getCourse ();

    public Student setStudent(Student student);
    public Integer setScore(Integer score);
    public Course setCourse (Course course);

    public Student addStudent(Student student);
    public Integer addScore(Integer score);
    public Course addCourse (Course course);

    public Student deleteStudent(Student student);
    public Integer deleteScore(Integer score);
    public Course deleteCourse (Course course);

}
