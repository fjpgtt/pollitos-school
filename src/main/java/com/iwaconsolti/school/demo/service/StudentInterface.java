package com.iwaconsolti.school.demo.service;

import com.iwaconsolti.school.demo.entity.CourseEntity;
import com.iwaconsolti.school.demo.entity.StudentEntity;

import java.util.List;

public interface StudentInterface {
    List<StudentEntity> findAllStudents();
    StudentEntity saveStudent(StudentEntity studentEntity);
    StudentEntity updateStudent(Integer id, StudentEntity studentEntity);
    void deleteStudent(Integer id);
}
