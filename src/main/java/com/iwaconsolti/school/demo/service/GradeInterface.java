package com.iwaconsolti.school.demo.service;

import com.iwaconsolti.school.demo.entity.GradeEntity;

import java.util.List;

public interface GradeInterface {
    List<GradeEntity> findAllGrades();
}
