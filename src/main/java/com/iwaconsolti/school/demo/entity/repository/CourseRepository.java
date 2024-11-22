package com.iwaconsolti.school.demo.entity.repository;

import com.iwaconsolti.school.demo.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Integer> {

}
