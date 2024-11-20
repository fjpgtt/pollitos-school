package com.iwaconsolti.school.repository;

import com.iwaconsolti.school.model.Grade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GradeRepository extends JpaRepository<Grade, Integer> {
    List<Grade> findByCourseId(Integer courseId);
    List<Grade> findByStudentId(Integer studentId);

}
