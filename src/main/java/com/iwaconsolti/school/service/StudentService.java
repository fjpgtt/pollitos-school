package com.iwaconsolti.school.service;

import com.iwaconsolti.school.model.Student;

import java.util.List;
import java.util.Optional;

public interface StudentService {

    public Student createStudent(Student student);

    public Student updateStudent(int id, Student student, String schoolName);

    public void deleteStudent(int id);

    public List<Student> getAllStudentsBySchool(String schoolName) ;

    public Optional<Student> getStudentById(int id, String schoolName);
    }
