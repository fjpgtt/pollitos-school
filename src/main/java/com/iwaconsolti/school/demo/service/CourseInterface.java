package com.iwaconsolti.school.demo.service;

import com.iwaconsolti.school.demo.entity.CourseEntity;

import java.util.List;
import java.util.Optional;

public interface CourseInterface {
    List<CourseEntity> findAllCourses();
    CourseEntity saveCourse(CourseEntity courseEntity);
    CourseEntity updateCourse(Integer id, CourseEntity courseEntity);
    void deleteCourse(Integer id);

    ////// Metodos con Querys ///////////
    Optional<CourseEntity> findCourseByNameWithJPQL(String name);

    // Consulta con Inversión de Control
    Optional<CourseEntity> findByNameIgnoreCase(String name);

}
