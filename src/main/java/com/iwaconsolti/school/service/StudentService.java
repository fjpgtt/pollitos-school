package com.iwaconsolti.school.service;

import com.iwaconsolti.school.model.Student;

import java.util.List;
import java.util.Optional;

public interface StudentService {

    Student createStudent(Student student);

    Student updateStudent(int id, Student student, int schoolId);

    void deleteStudent(int id);

    List<Student> getAllStudentsBySchool(int schoolId) ;

    Optional<Student> getStudentById(int id, int schoolId);
    }
