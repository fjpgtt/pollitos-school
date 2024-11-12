package com.iwaconsolti.school.service;

import com.iwaconsolti.school.model.School;
import com.iwaconsolti.school.model.Student;

import java.util.List;

public interface StudentService {
    List<Student> getStudents(School school);

    Student editStudent(School school, int studentId, Student student);

}
