package com.iwaconsolti.school.demo.service;

import com.iwaconsolti.school.demo.entity.StudentEntity;

import java.util.List;

public interface StudentInterface {
    List<StudentEntity> findAllStudents();
}
