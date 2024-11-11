package com.iwaconsolti.school.service;

import com.iwaconsolti.school.model.Student;

import java.util.List;

public interface StudentService {
    List<Student> getStudents();

    Student editStudent(Student student);

}
