package com.iwaconsolti.school.service;

import com.iwaconsolti.school.model.Student;

import java.util.List;

public interface StudentService {
    public List<Student> getStudents();

    public Student getStudentById(Integer id);

    public Student setStudent(Student student);

    public Student addStudent(Student student);

    public void deleteStudent(Integer id);
}
