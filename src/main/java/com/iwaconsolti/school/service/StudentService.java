package com.iwaconsolti.school.service;

import com.iwaconsolti.school.model.Student;

import java.util.List;
import java.util.Optional;

public interface StudentService {

    public Student createStudent(Student student);

    public Student updateStudent(int id, Student student, int schoolId);

    public void deleteStudent(int id);

    public List<Student> getAllStudentsBySchool(int schoolId) ;

    public Optional<Student> getStudentById(int id, int schoolId);
    }
