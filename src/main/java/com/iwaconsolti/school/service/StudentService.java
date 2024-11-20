package com.iwaconsolti.school.service;

import com.iwaconsolti.school.model.Student;

import java.util.List;
import java.util.Optional;

public interface StudentService {

    public List<Student> getAllStudents();

    public Optional<Student> getStudentById(int id);

    public Student createStudent(Student student);

    public Student updateStudent(int id, Student student);

    public void deleteStudent(int id);

}
